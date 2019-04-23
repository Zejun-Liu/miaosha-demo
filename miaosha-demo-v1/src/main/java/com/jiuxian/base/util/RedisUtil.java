package com.jiuxian.base.util;

import com.jiuxian.base.util.redis.*;
import com.jiuxian.base.web.WebContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-23 12:51:00
 * Comment:
 */
public class RedisUtil {

    private RedisUtil() {
    }

    private final static RedisTemplate<String, String> redisTemplate =
            WebContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);

    public static Boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public static Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public static Long delete(String... key) {
        return redisTemplate.delete(Arrays.asList(key));
    }

    public final static ForString forString = new ForString(redisTemplate.opsForValue());

    public final static ForHash forHash = new ForHash(redisTemplate.opsForHash());

    public final static ForList forList = new ForList(redisTemplate.opsForList());

    public final static ForZSet forZSet = new ForZSet(redisTemplate.opsForZSet());

    public final static ForGeo forGeo = new ForGeo(redisTemplate.opsForGeo());
}
