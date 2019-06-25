package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ForSet {
    private SetOperations<String, String> operations;

    public ForSet(SetOperations<String, String> operations) {
        this.operations = operations;
    }


    public Long add(String key, String... values) {
        return operations.add(key, values);
    }


    public Long remove(String key, Object... values) {
        return operations.remove(key, values);
    }


    public String pop(String key) {
        return operations.pop(key);
    }


    public List<String> pop(String key, long count) {
        return operations.pop(key, count);
    }


    public Boolean move(String key, String value, String destKey) {
        return operations.move(key, value, destKey);
    }


    public Long size(String key) {
        return operations.size(key);
    }


    public Boolean isMember(String key, String value) {
        return operations.isMember(key, value);
    }


    public Set<String> intersect(String key, String otherKey) {
        return operations.intersect(key, otherKey);
    }


    public Set<String> intersect(String key, Collection<String> otherKeys) {
        return operations.intersect(key, otherKeys);
    }


    public Long intersectAndStore(String key, String otherKey, String destKey) {
        return operations.intersectAndStore(key, otherKey, destKey);
    }


    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return operations.intersectAndStore(key, otherKeys, destKey);
    }


    public Set<String> union(String key, String otherKey) {
        return operations.union(key, otherKey);
    }


    public Set<String> union(String key, Collection<String> otherKeys) {
        return operations.union(key, otherKeys);
    }


    public Long unionAndStore(String key, String otherKey, String destKey) {
        return operations.unionAndStore(key, otherKey, destKey);
    }


    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return operations.unionAndStore(key, otherKeys, destKey);
    }


    public Set<String> difference(String key, String otherKey) {
        return operations.difference(key, otherKey);
    }


    public Set<String> difference(String key, Collection<String> otherKeys) {
        return operations.difference(key, otherKeys);
    }


    public Long differenceAndStore(String key, String otherKey, String destKey) {
        return operations.differenceAndStore(key, otherKey, destKey);
    }


    public Long differenceAndStore(String key, Collection<String> otherKeys, String destKey) {
        return operations.differenceAndStore(key, otherKeys, destKey);
    }


    public Set<String> members(String key) {
        return operations.members(key);
    }


    public String randomMember(String key) {
        return operations.randomMember(key);
    }


    public Set<String> distinctRandomMembers(String key, long count) {
        return operations.distinctRandomMembers(key, count);
    }


    public List<String> randomMembers(String key, long count) {
        return operations.randomMembers(key, count);
    }


    public Cursor<String> scan(String key, ScanOptions options) {
        return operations.scan(key, options);
    }


    public RedisOperations<String, String> getOperations() {
        return operations.getOperations();
    }
}

