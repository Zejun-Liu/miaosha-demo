package com.jiuxian.base.rediskey;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 13:34:00
 * Comment:
 */


public abstract class AbstractRedisPrefix implements RedisPrefix {


    private int expireSeconds;
    private String prefix;

    AbstractRedisPrefix(String prefix, int expireSeconds) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    AbstractRedisPrefix(String prefix) {
        this(prefix, 0);
    }


    @Override
    public String prefix() {
        return this.getClass().getSimpleName() + ":" + prefix;
    }


    @Override
    public int expireSeconds() {
        return expireSeconds;
    }
}
