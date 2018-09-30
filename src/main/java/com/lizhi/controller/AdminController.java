package com.lizhi.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lizhi.service.RedisService;
import com.lizhi.shiro.realm.dao.UserDaoImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizhi.bean.CacheUtils;
import com.lizhi.bean.FileType;
import com.lizhi.bean.ResponseBean;
import com.lizhi.bean.TFile;
import com.lizhi.bean.User;
import com.lizhi.service.IImageService;
import com.lizhi.service.IUserService;
import com.lizhi.shiro.realm.dao.UserDao;

/**
 * Created by xinghailong on 2017/7/21.
 */
@Controller
public class AdminController {

	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	public static  final String SESSION_KEY = "session%s";

	@Resource
	private DefaultWebSecurityManager securityManager;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IImageService imageService;

	@Resource
	private RedisService redisService;

	@Value("${test.url}")
	private String Hosturl;

	@Resource
	private UserDao userDao;
	/**
	 * 返回主页面
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/index" })
	public String hello() {
		return "index";
	}
	

	/**
	 * 登录
	 * 登出
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/admin/{url}", method = RequestMethod.GET)
	public String login(@PathVariable("url") String url) {
		return url;
	}
	
	/**
	 * 钟
	 * 聊天室
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/compont/{url}", method = RequestMethod.GET)
	public String defi(@PathVariable("url") String url) {
		return url;
	}
	
	/**
	 * 登陆后看重
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public void subLogin(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		request.getServletContext().setAttribute("Hosturl", Hosturl);
		try {
			//开始验证登录
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			// token.setRememberMe(user.isRemenberMe());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			user = userDao.getUser(user.getUsername());
			//获取头像图片地址
			TFile tFileByCreatorId = imageService.getTFileByCreatorIdAndType(user.getId(),FileType.FILE_TYPE_ICO);
			user.setIco(tFileByCreatorId == null ? "assets/images/logo.png":tFileByCreatorId.getFileUrl());
			session.setAttribute("user",user);
//			redisService.set(SESSION_KEY+session.getId(),session);
//			subject.checkRoles("admin");
//			if (subject.hasRole("admin")) {
//
//			}
		} catch (Exception e) {
			//cache中的session删除
			redisService.remove(UserDaoImpl.USER_KEY+user.getUsername());
			log.error("Exception:",e);
		}
		/**
		 * 注意： "redirect:"后面跟着的是"/"和不跟着"/"是不一样的： 1) "redirect:"后面跟着"/"：
		 * 说明该URI是相对于项目的Context ROOT的相对路径 2) "redirect:"后面没有跟着"/"： 说明该URI是相对于当前路径
		 */
		response.sendRedirect("../index");
	}
	
	/**
	 * 注销登录入口
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public void subLogOut( HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String sessionId = "sessionId:"+session.getId();
		String username = ((User)session.getAttribute("user")).getUsername();
		if(username == null) {
			return;
		}
		//session 中user删除
		session.removeAttribute("user");
		//cache中的session删除
		redisService.remove(UserDaoImpl.USER_KEY+username);
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			log.warn("注销登录：{}",username);
		} catch (Exception e) {
			log.error("Exception:",e);
		}
		/**
		 * 注意： "redirect:"后面跟着的是"/"和不跟着"/"是不一样的： 1) "redirect:"后面跟着"/"：
		 * 说明该URI是相对于项目的Context ROOT的相对路径 2) "redirect:"后面没有跟着"/"： 说明该URI是相对于当前路径
		 */
		response.sendRedirect("../index");
	}
	
	/**
	 * 注册入口
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws CloneNotSupportedException 
	 */
	@RequestMapping(value = "/admin/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public void subRegister(User user, HttpServletRequest request, HttpServletResponse response) throws IOException, CloneNotSupportedException {
		User oneByName = userService.getOneByName(user.getUsername());
		System.out.println(oneByName);
		if(oneByName != null) {
			System.out.println("已经有这个");
			return;
		}
		User newUser = (User)user.clone();
		user.setSalt(user.getUsername());
		user.setPassword(new Md5Hash(user.getPassword(),user.getSalt()).toString());
		
		userService.insertUser(user);
		subLogin(newUser,request,response);
	}
	
	/**
	 * 注册入口
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws CloneNotSupportedException 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String test() throws IOException, CloneNotSupportedException {
		return "test";
		
	}
	
}