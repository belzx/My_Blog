package com.lizhi.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.lizhi.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 */
@Controller
public class AdminController{

	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	@Value("${host.url}")
	private String hosturl;

	@Autowired
	private ServletContext servletContext;

	@PostConstruct
	public void init(){
		servletContext.setAttribute("hosturl", hosturl);
	}

	@RequestMapping(path = "/admin/{html}")
	public String commonView(@PathVariable String html, HttpServletRequest request,CURDParam curdParam){
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.entrySet().stream().forEach(d ->{
			String[] value = d.getValue();
			if(value == null || value.length == 0){
			}else {
				request.setAttribute(d.getKey(),d.getValue()[0]);
			}
		});
		return "/admin/"+html;
	}

	@GetMapping(path = "/")
	public String index(){
		return "/admin/index";
	}

}