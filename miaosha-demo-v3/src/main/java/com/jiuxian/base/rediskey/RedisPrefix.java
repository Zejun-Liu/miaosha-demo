package com.jiuxian.base.rediskey;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 13:35:00
 * Comment:
 */

public interface RedisPrefix {

    String prefix();

    String key();

    int expireSeconds();
}
