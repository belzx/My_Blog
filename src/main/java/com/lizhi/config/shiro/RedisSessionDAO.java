package com.lizhi.config.shiro;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
 import javax.annotation.Resource;

import com.lizhi.service.RedisService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
 /**
 * redis实现共享session
 */
@Component("sessionDAO")
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

     private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

     @Autowired
     private RedisService redisService;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisService.setSession(session);
        return sessionId;
    }

     /**
      * 根据前端cookie保存的sessionid 获取到session
      *
      * @param sessionId
      * @return
      */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if(session == null){
            session = (Session) redisService.getSession(sessionId.toString());
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        if (!redisService.existsSession(session)) {
            redisService.setSession(session);
        }
        // session 在redis过期时间是30分钟30*60
        redisService.updateSessionTimeOut(session);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisService.deleteSession(session);
    }
}