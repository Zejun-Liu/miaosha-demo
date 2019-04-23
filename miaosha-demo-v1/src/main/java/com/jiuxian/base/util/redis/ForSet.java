package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.SetOperations;

public class ForSet {
    private SetOperations<String, String> operations;

    public ForSet(SetOperations<String, String> operations) {
        this.operations = operations;
    }
}

