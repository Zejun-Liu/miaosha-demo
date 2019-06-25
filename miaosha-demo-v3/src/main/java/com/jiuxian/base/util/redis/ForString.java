package com.jiuxian.base.util.redis;

import com.jiuxian.base.util.JSONUtil;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ForString {

    private ValueOperations<String, String> operations;

    public ForString(ValueOperations<String, String> operations) {
        this.operations = operations;
    }

    public <T> void set(String key, T value) {
        if (value == null) return;
        operations.set(key, JSONUtil.toJSON(value));
    }

    public <T> void set(String key, T value, long seconds) {
        if (value == null) return;
        operations.set(key, JSONUtil.toJSON(value), seconds, TimeUnit.SECONDS);
    }

    public <T> T get(String key, Class<T> clazz) {
        String json = operations.get(key);
        if (StringUtils.isEmpty(json)) return null;
        return JSONUtil.toBean(json, clazz);
    }

    public <T> T get(String key, long start, long end, Class<T> clazz) {
        String json = operations.get(key, start, end);
        if (StringUtils.isEmpty(json)) return null;
        return JSONUtil.toBean(json, clazz);
    }

    public <T> Boolean setIfAbsent(String key, T value) {
        if (value == null) return false;
        return operations.setIfAbsent(key, JSONUtil.toJSON(value));
    }

    public <T> Boolean setIfAbsent(String key, T value, long seconds) {
        if (value == null) return false;
        return operations.setIfAbsent(key, JSONUtil.toJSON(value), seconds, TimeUnit.SECONDS);
    }

    public <T> Boolean setIfPresent(String key, T value) {
        if (value == null) return false;
        return operations.setIfPresent(key, JSONUtil.toJSON(value));
    }

    public <T> Boolean setIfPresent(String key, T value, long seconds) {
        if (value == null) return false;
        return operations.setIfPresent(key, JSONUtil.toJSON(value), seconds, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAndSet(String key, T value) {
        if (value == null) return null;
        String oldValue = operations.getAndSet(key, JSONUtil.toJSON(value));
        if (oldValue == null) return null;
        return (T) JSONUtil.toBean(oldValue, value.getClass());
    }

    public <T> void multiSet(Map<String, T> map) {
        if (map == null) return;
        Map<String, String> stringMap = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, JSONUtil::toJSON));
        operations.multiSet(stringMap);
    }

    public <T> Boolean multiSetIfAbsent(Map<String, T> map) {
        if (map == null) return false;
        Map<String, String> stringMap = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, JSONUtil::toJSON));
        return operations.multiSetIfAbsent(stringMap);
    }

    public <T> List<T> multiGet(Collection<String> keys, Class<T> clazz) {
        List<String> list = operations.multiGet(keys);
        if (list == null) return Collections.emptyList();
        return list.stream().map(s -> JSONUtil.toBean(s, clazz)).collect(Collectors.toList());
    }

    public Long increment(String key) {
        return operations.increment(key);
    }

    public Long increment(String key, long delta) {
        return operations.increment(key, delta);
    }

    public Double increment(String key, double delta) {
        return operations.increment(key, delta);
    }

    public Long decrement(String key) {
        return operations.decrement(key);
    }

    public Long decrement(String key, long delta) {
        return operations.decrement(key, delta);
    }

    public <T> Integer append(String key, T value) {
        if (value == null) return null;
        return operations.append(key, JSONUtil.toJSON(value));
    }

    public Long size(String key) {
        return operations.size(key);
    }

    public Boolean setBit(String key, long offset, boolean value) {
        return operations.setBit(key, offset, value);
    }

    public Boolean getBit(String key, long offset) {
        return operations.getBit(key, offset);
    }

    public List<Long> bitField(String key, BitFieldSubCommands subCommands) {
        return operations.bitField(key, subCommands);
    }

    public RedisOperations<String, String> getOperations() {
        return operations.getOperations();
    }
}