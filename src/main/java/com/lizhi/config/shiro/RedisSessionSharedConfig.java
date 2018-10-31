package com.lizhi.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 /**
 * Created by alexd on 2017/12/30.
 */
//@ConfigurationProperties(prefix = "redisSessionShared")
@Component
public class RedisSessionSharedConfig {
    public static Long REDISCONFIGFORSESSION_DEFAULTEXPIRATION = 1L;
    public static Long SHIROCACHE_GLOBEXPIRE = 2L;
    public static int REDISSESSIONDAO_EXPIRETIME = 1800;
    public static String REDISSESSIONDAO_PREFIX = "4";
    public static String SHIROCACHE_REDIS_SHIRO_CACHE = "5";
     public static Long getRedisconfigforsessionDefaultexpiration() {
        return REDISCONFIGFORSESSION_DEFAULTEXPIRATION;
    }
     public static void setRedisconfigforsessionDefaultexpiration(Long redisconfigforsessionDefaultexpiration) {
        REDISCONFIGFORSESSION_DEFAULTEXPIRATION = redisconfigforsessionDefaultexpiration;
    }
     public static Long getShirocacheGlobexpire() {
        return SHIROCACHE_GLOBEXPIRE;
    }
     public static void setShirocacheGlobexpire(Long shirocacheGlobexpire) {
        SHIROCACHE_GLOBEXPIRE = shirocacheGlobexpire;
    }
     public static int getRedissessiondaoExpiretime() {
        return REDISSESSIONDAO_EXPIRETIME;
    }
     public static void setRedissessiondaoExpiretime(int redissessiondaoExpiretime) {
        REDISSESSIONDAO_EXPIRETIME = redissessiondaoExpiretime;
    }
     public static String getRedissessiondaoPrefix() {
        return REDISSESSIONDAO_PREFIX;
    }
     public static void setRedissessiondaoPrefix(String redissessiondaoPrefix) {
        REDISSESSIONDAO_PREFIX = redissessiondaoPrefix;
    }
     public static String getShirocacheRedisShiroCache() {
        return SHIROCACHE_REDIS_SHIRO_CACHE;
    }
     public static void setShirocacheRedisShiroCache(String shirocacheRedisShiroCache) {
        SHIROCACHE_REDIS_SHIRO_CACHE = shirocacheRedisShiroCache;
    }
}