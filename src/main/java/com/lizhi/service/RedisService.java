package com.lizhi.service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.lizhi.config.shiro.RedisSessionSharedConfig;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * redicache 工具类
 *
 */
@Service
public class RedisService {
    /**
     * redisTemplate.opsForValue();//操作字符串
     * redisTemplate.opsForHash();//操作hash
     * redisTemplate.opsForList();//操作list
     * redisTemplate.opsForSet();//操作set
     * redisTemplate.opsForZSet();//操作有序set
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }


    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        try {
            return redisTemplate.opsForValue().get(key);
//            byte[] o = (byte[])redisTemplate.opsForValue().get(key);
//            if(null == o) return null;
//            return org.springframework.util.SerializationUtils.deserialize(o);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        try {
//            redisTemplate.opsForValue().set(key, org.springframework.util.SerializationUtils.serialize(value));
            redisTemplate.opsForValue().set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean existsSession(Session session){
        try {
          return  exists(RedisSessionSharedConfig.REDISSESSIONDAO_PREFIX + session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteSession(Session session){
        try {
            remove(RedisSessionSharedConfig.REDISSESSIONDAO_PREFIX + session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSessionTimeOut(Session session){
        try {
            expire(RedisSessionSharedConfig.REDISSESSIONDAO_PREFIX+session.getId().toString(), RedisSessionSharedConfig.REDISSESSIONDAO_EXPIRETIME, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSession(Session session){
        try {
            redisTemplate.opsForValue().set(RedisSessionSharedConfig.REDISSESSIONDAO_PREFIX+session.getId(),session,RedisSessionSharedConfig.REDISSESSIONDAO_EXPIRETIME,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session getSession(String  sessionid){
        try {
          return  (Session)get(RedisSessionSharedConfig.REDISSESSIONDAO_PREFIX+sessionid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  boolean expire(String key,int expireTime,TimeUnit timeUnit) {
      return  redisTemplate.expire(key, RedisSessionSharedConfig.REDISSESSIONDAO_EXPIRETIME, TimeUnit.SECONDS);
    }

    public  Map<String,String> hmget(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void lset(String key,Object object){
        try {
            redisTemplate.opsForList().leftPush(key, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object lget(String key){
        try {
          return   redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object lgetAll(String key){
        List<Object> o = new ArrayList();
        try {
            Object lget;
            while((lget = lget(key)) != null){
                o.add(lget);
            }
            return  o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}