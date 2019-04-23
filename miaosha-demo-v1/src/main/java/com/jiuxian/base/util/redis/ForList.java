package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.ListOperations;

public class ForList {

    private ListOperations<String, String> operations;

    public ForList(ListOperations<String, String> operations) {
        this.operations = operations;
    }
}
