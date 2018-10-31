package com.lizhi.controller;

import com.lizhi.bean.*;
import com.lizhi.service.ITFileService;
import com.lizhi.service.IUserService;
import com.lizhi.service.RedisService;
import com.lizhi.shiro.realm.dao.UserDao;
import com.lizhi.shiro.realm.dao.UserDaoImpl;
import com.lizhi.utils.StringUtil;
import com.lizhi.utils.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private DefaultWebSecurityManager securityManager;

    @Resource
    private IUserService userService;

    @Resource
    private ITFileService itFileService;

    @Resource
    private RedisService redisService;

    @Resource
    private UserDao userDao;


    /**
     * 登陆后看重
     *
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseMessage subLogin(User user, HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(StringUtil.isNullOrEmpty(user.getUsername())){
            return ResponseMessage.error("账号不能为空");
        }

        if(StringUtil.isNullOrEmpty(user.getPassword())){
            return ResponseMessage.error("密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), new Md5Hash(user.getPassword()).toString(),user.isRememberMe());

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);//开始验证登录
            token.setRememberMe(user.isRememberMe());//设置rememberMe
            user = userDao.getUser(user.getUsername());//会从redis中获取user
            user.setLastLoginTime(new Date());
            userService.update(user);//更新登录时间
            //获取头像图片地址
//            List<TFile> tFileByCreatorId = itFileService.query(CURDParam.getInstans().where("id", user.getId()).where("fileType", FileType.FILE_TYPE_ICO));
//            user.setIco(tFileByCreatorId.isEmpty() ? "assets/images/logo.png" : tFileByCreatorId.get(0).getFileUrl());

            //user注入session
            session.setAttribute("user", user);
//			subject.checkRoles("admin");
        } catch (Exception e) {
            userDao.clearCaheUser(UserDaoImpl.USER_KEY + user.getUsername());
            log.error("Exception:", e);
            return ResponseMessage.error("用户名或账号错误");
        }

        String oldUrl = "";
        //获取之前的地址
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest != null){
             oldUrl = savedRequest.getRequestUrl();
        }
        String requestUri = WebUtils.getRequestUri(request);

        ResponseMessage<Object> ok = ResponseMessage.ok();
        if(!requestUri.equals(oldUrl)){
            ok.setStatus(302);
            ok.setMessage(oldUrl);
        }

        return ok;
    }



    /**
     * 注销登录入口
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseMessage subLogOut(HttpServletRequest request) throws IOException {
        try {
            SecurityUtils.getSubject().logout();
            User onlineUser = WebUtil.getOnlineUser();
            if(onlineUser != null){
                request.getSession().removeAttribute("user");
                userDao.clearCaheUser(onlineUser.getUsername());
            }
        } catch (Exception e) {
            log.error("注销登录失败:", e);
        }
        return ResponseMessage.ok();
    }

    /**
     * 注册入口
     *
     * @param user
     * @return
     * @throws IOException
     * @throws CloneNotSupportedException
     */
    @PostMapping(path = {"/register"})
    public ResponseMessage register(User user, HttpServletRequest request, HttpServletResponse response) {

        if(StringUtil.isNullOrEmpty(user.getUsername())){
            return ResponseMessage.error("账号不能为空");
        }

        if(StringUtil.isNullOrEmpty(user.getPassword())){
            return ResponseMessage.error("密码不能为空");
        }

        int number = userService.count(CURDParam.getInstans().where("username", user.getUsername()));
        if (number > 0) {
            log.warn("此用户名已经存在:{}",user.getUsername(),user.getId());
            return ResponseMessage.error("此用户名已经存在");
        }
        String password = user.getPassword();
        user.setPassword(new Md5Hash(password).toString());
        user.setId(StringUtil.getUUID());
        user.setCreateTime(new Date());
        userService.insert(user);
        user.setPassword(password);
        subLogin(user,request,response);
		return ResponseMessage.ok();
    }

}
