package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForList {

    private ListOperations<String, String> operations;

    public ForList(ListOperations<String, String> operations) {
        this.operations = operations;
    }


    public List<String> range(String key, long start, long end) {
        return operations.range(key, start, end);
    }


    public void trim(String key, long start, long end) {
        operations.trim(key, start, end);
    }


    public Long size(String key) {
        return operations.size(key);
    }


    public Long leftPush(String key, String value) {
        return operations.leftPush(key, value);
    }


    public Long leftPushAll(String key, String... values) {
        return operations.leftPushAll(key, values);
    }


    public Long leftPushAll(String key, Collection<String> values) {
        return operations.leftPushAll(key, values);
    }


    public Long leftPushIfPresent(String key, String value) {
        return operations.leftPushIfPresent(key, value);
    }


    public Long leftPush(String key, String pivot, String value) {
        return operations.leftPush(key, pivot, value);
    }


    public Long rightPush(String key, String value) {
        return operations.rightPush(key, value);
    }


    public Long rightPushAll(String key, String... values) {
        return operations.rightPushAll(key, values);
    }


    public Long rightPushAll(String key, Collection<String> values) {
        return operations.rightPushAll(key, values);
    }


    public Long rightPushIfPresent(String key, String value) {
        return operations.rightPushIfPresent(key, value);
    }


    public Long rightPush(String key, String pivot, String value) {
        return operations.rightPush(key, pivot, value);
    }


    public void set(String key, long index, String value) {
        operations.set(key, index, value);
    }


    public Long remove(String key, long count, Object value) {
        return operations.remove(key, count, value);
    }


    public String index(String key, long index) {
        return operations.index(key, index);
    }


    public String leftPop(String key) {
        return operations.leftPop(key);
    }


    public String leftPop(String key, long timeout, TimeUnit unit) {
        return operations.leftPop(key, timeout, unit);
    }


    public String rightPop(String key) {
        return operations.rightPop(key);
    }


    public String rightPop(String key, long timeout, TimeUnit unit) {
        return operations.rightPop(key, timeout, unit);
    }


    public String rightPopAndLeftPush(String sourceKey, String destinationKey) {
        return operations.rightPopAndLeftPush(sourceKey, destinationKey);
    }


    public String rightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return operations.rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }


    public RedisOperations<String, String> getOperations() {
        return operations.getOperations();
    }
}
