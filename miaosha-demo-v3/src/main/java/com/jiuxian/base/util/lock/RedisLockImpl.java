package com.jiuxian.base.util.lock;

import com.jiuxian.base.util.RedisUtil;
import org.springframework.stereotype.Component;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-06 11:48:45
 * *
 * @description:
 **/
//@Component
public class RedisLockImpl implements LockService<Long> {
    @Override
    public boolean setIfAbsent(String key, Long value) {
        return RedisUtil.forString.setIfAbsent(key, value);
    }

    @Override
    public void expire(String key, int seconds) {
        RedisUtil.expire(key, seconds);
    }

    @Override
    public Long get(String key) {
        return RedisUtil.forString.get(key, Long.class);
    }

    @Override
    public Long getAndSet(String key, Long value) {
        return RedisUtil.forString.getAndSet(key, value);
    }

    @Override
    public void release(String key) {
        RedisUtil.delete(key);
    }
}
