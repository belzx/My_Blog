package com.lizhi.config.shiro;

import com.lizhi.controller.UserController;
import com.lizhi.shiro.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.AbstractSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 权限注解：
 * <p>
 * Shiro 提供了相应的注解用于权限控制，如果使用这些注解就需要使用AOP 的功能来进行
 * 判断，如Spring AOP；Shiro 提供了Spring AOP 集成用于权限注解的解析和验证。
 * <p>
 * 　　@RequiresAuthentication
 * 　　表示当前Subject已经通过login 进行了身份验证；即Subject. isAuthenticated()返回true。
 * <p>
 * 　　@RequiresUser
 * 　　表示当前Subject已经身份验证或者通过记住我登录的。
 * <p>
 * 　　@RequiresGuest
 * 　　表示当前Subject没有身份验证或通过记住我登录过，即是游客身份。
 * <p>
 * 　　@RequiresRoles(value={“admin”, “user”}, logical= Logical.AND)
 * 　　@RequiresRoles(value={“admin”})
 * 　　@RequiresRoles({“admin“})
 * 　　表示当前Subject需要角色admin 和user。
 * <p>
 * 　　@RequiresPermissions (value = { “ user : a ”, “ user : b ” }, logical = Logical.OR)
 * 　　表示当前Subject需要权限user:a或user:b。
 * <p>
 * 既可以用在controller中，也可以用在service中
 * 建议将shiro注解放入controller，因为如果service层使用了spring的事物注解，那么shiro注解将无效
 */
@Configuration // 等价于beans
public class ShiroConfig {
    private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/login");
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        // 拦截器.
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/admin/write", "authc");
        map.put("/admin/photo", "authc");
        map.put("/admin/write.html", "authc");
        map.put("/admin/photo.html", "authc");
        map.put("/**", "rememberMyFilter");
//        map.put("/assets/**", "anon");
//        map.put("/testrole1", "roles[\"admin\",\"user\"]"); //<!--表示同时要具备两个角色才行-->
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("rememberMyFilter",new RememberAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    public CookieRememberMeManager rememberMeManager(){
        log.info("注入Shiro的记住我(CookieRememberMeManager)管理器-->rememberMeManager", CookieRememberMeManager.class);
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecretKey deskey = keygen.generateKey();
        //System.out.println(Base64.encodeToString(deskey.getEncoded()));
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherKey(cipherKey);
//        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间,默认30天 ,单位秒：60 * 60 * 24 * 30
        simpleCookie.setMaxAge(259200);

        return simpleCookie;
    }

    public SimpleCookie sessionIdCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("sidssss");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(60*5);
        return simpleCookie;
    }


    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") CustomRealm authRealm,@Qualifier("sessionManager") SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    /**
     * @param sessionDAO
     * @return
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(@Qualifier("sessionDAO")SessionDAO sessionDAO){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为3小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT * 6);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setDeleteInvalidSessions(true);// 删除过期的session
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());

        return sessionManager;
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
//        myAuthorizingRealm.setCredentialsMatcher(matcher); // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        // 设置缓存管理器
//        myAuthorizingRealm.setCacheManager(ehCacheManager);

        return myAuthorizingRealm;
    }

    /**
     * 密码匹配凭证管理器
     * 暂时不使用
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于
        return hashedCredentialsMatcher;
    }

    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
//        System.out.println("开启了Shiro注解支持");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
