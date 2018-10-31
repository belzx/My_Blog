package com.lizhi.utils;

import com.lizhi.bean.User;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtil {
    //获取request，线程安全
    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if(requestAttributes == null){
            return null;
        }
        return ((ServletRequestAttributes)requestAttributes).getRequest();
    }

    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        return request == null?null:request.getSession();
    }

    public static User getOnlineUser(){
        return  getSession() == null? null :(User)getSession().getAttribute("user");
    }

    //获取上下文
    //@postContrust使用可能会为空
    public static ServletContext getContext(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
}
