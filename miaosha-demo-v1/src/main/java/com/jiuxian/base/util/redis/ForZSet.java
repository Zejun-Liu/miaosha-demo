package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.ZSetOperations;

public class ForZSet {

    private ZSetOperations<String, String> operations;

    public ForZSet(ZSetOperations<String, String> operations) {
        this.operations = operations;
    }
}