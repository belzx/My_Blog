package com.lizhi.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 常用地址配置
 */
@Configuration
public class CustomViewConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login.html").setViewName("/admin/login");
//        registry.addViewController("/index.html").setViewName("/admin/index");
//        registry.addViewController("/about.html").setViewName("/admin/about");
//        registry.addViewController("/index.html").setViewName("/admin/index");
    }
}
