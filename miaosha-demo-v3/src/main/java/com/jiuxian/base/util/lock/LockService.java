package com.jiuxian.base.util.lock;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-06 11:42:30
 * *
 * @description:
 **/
public interface LockService<T> {

    boolean setIfAbsent(String key, T value);

    void expire(String key, int seconds);

    T get(String key);

    T getAndSet(String key, T value);

    void release(String key);
}

