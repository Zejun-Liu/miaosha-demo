package com.jiuxian.base.rediskey;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 15:55:00
 * Comment:
 */


public class SeckillOrderKey extends AbstractRedisPrefix {

    private SeckillOrderKey(String prefix, int expireSeconds) {
        super(prefix, expireSeconds);
    }

    public static SeckillOrderKey signKey = new SeckillOrderKey("sign", 60 * 10);

    @Override
    public String key() {
        return null;
    }
}
