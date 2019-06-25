package com.jiuxian.base.util.lock;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-25 17:16:13
 * *
 * @description:
 **/
@Component
public class MapLockImpl implements LockService<Long> {

    private final static Map<String, Long> content = new ConcurrentHashMap<>(16);

    @Override
    public synchronized boolean setIfAbsent(String key, Long value) {
        if (content.containsKey(key)) return false;
        content.put(key, value);
        return true;
    }

    @Override
    public void expire(String key, int seconds) {

    }

    @Override
    public Long get(String key) {
        return content.get(key);
    }

    @Override
    public synchronized Long getAndSet(String key, Long value) {
        Long oldValue = content.get(key);
        content.put(key, value);
        return oldValue;
    }

    @Override
    public void release(String key) {
        content.remove(key);
    }
}
