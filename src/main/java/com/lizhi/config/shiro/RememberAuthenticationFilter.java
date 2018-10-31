package com.lizhi.config.shiro;

import com.lizhi.bean.User;
import com.lizhi.shiro.realm.dao.UserDao;
import com.lizhi.utils.SpringUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

public class RememberAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * @return false 表示需要登录，会返回登录界面
     * true 表示不需要登录。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Object principal = subject.getPrincipal();
        if(principal == null){//
            return true;
        }
        PrincipalCollection principals = subject.getPrincipals();
        if (!subject.isAuthenticated() && subject.isRemembered()) {//没登陆+通过记住

            //通过rememberMe获取信息然后做登录处理
           User user = SpringUtil.getBean(UserDao.class).getUser(principal.toString());
           if(user == null){
               return false;
           }

           UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(),true);
            try {
                subject.login(token);
                ((HttpServletRequest) request).getSession().setAttribute("user",user);
                System.out.println(subject.isAuthenticated());
                }catch (Exception e){
                    return false;
                }
        }
        return true;
    }
}