package com.lizhi.config;

import com.lizhi.shiro.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration // 等价于beans
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        
        // 未授权界面，则自动跳转到下面的页面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/login");
        
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("websocked.ftl");

        // 拦截器.
        Map<String, String> map = new LinkedHashMap<String, String>();


        map.put("/", "anon");
        map.put("/index", "anon");
        map.put("/400", "anon");
        map.put("/404", "anon");
        map.put("/500", "anon");
        map.put("/error/**", "anon");
        map.put("/oto/**", "anon");
        //接口无需要授权的路径
        map.put("/admin/**", "anon");
        map.put("/compont/**", "anon");
        map.put("/file/**", "authc");
        map.put("/websocket", "anon");
        
        //资源访问路径
        map.put("/assets/**", "anon");
        map.put("/Wopop_files/**", "anon");
        map.put("/resource", "anon");
        map.put("/resource/**", "anon");
        
        //角色验证
//        map.put("/testrole", "roles[\"admin\"]");
//        map.put("/testrole1", "roles[\"admin\",\"user\"]"); //<!--表示同时要具备两个角色才行-->
        
        map.put("/test", "authc");//authc
        map.put("/**", "authc");//authc

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") CustomRealm authRealm ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(authRealm);

//        // 设置rememberMe管理器
//        securityManager.setRememberMeManager(cookieRememberMeManager);

        return securityManager;
    }

    /**
     * realm
     *
     * @return
     */
    @Bean(name = "authRealm")
    public CustomRealm myAuthRealm(
            @Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        CustomRealm myAuthorizingRealm = new CustomRealm();
        // 设置密码凭证匹配器
        myAuthorizingRealm.setCredentialsMatcher(matcher); // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        // 设置缓存管理器
//        myAuthorizingRealm.setCacheManager(ehCacheManager);

        return myAuthorizingRealm;
    }

    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于
        return hashedCredentialsMatcher;
    }
}
