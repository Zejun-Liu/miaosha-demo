package com.jiuxian.base.util.redis;

import com.jiuxian.base.util.JSONUtil;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ForHash {

    private HashOperations<String, String, String> operations;

    public ForHash(HashOperations<String, String, String> operations) {
        this.operations = operations;
    }

    public Long delete(String key, Object... hashKeys) {
        return operations.delete(key, hashKeys);
    }

    public Boolean hasKey(String key, Object hashKey) {
        return operations.hasKey(key, hashKey);
    }

    public <T> T get(String key, String hashKey, Class<T> clazz) {
        String json = operations.get(key, hashKey);
        if (StringUtils.isEmpty(json)) return null;
        return JSONUtil.toBean(json, clazz);
    }

    public <T> List<T> multiGet(String key, Collection<String> hashKeys, Class<T> clazz) {
        List<String> list = operations.multiGet(key, hashKeys);
        return list.stream().map(s -> JSONUtil.toBean(s, clazz)).collect(Collectors.toList());
    }

    public Long increment(String key, String hashKey, long delta) {
        return operations.increment(key, hashKey, delta);
    }

    public Double increment(String key, String hashKey, double delta) {
        return operations.increment(key, hashKey, delta);
    }

    public Set<String> keys(String key) {
        return operations.keys(key);
    }

    public Long lengthOfValue(String key, String hashKey) {
        return operations.lengthOfValue(key, hashKey);
    }

    public Long size(String key) {
        return operations.size(key);
    }

    public <T> void putAll(String key, Map<? extends String, T> map) {
        Map<String, String> stringMap = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> JSONUtil.toJSON(s.getValue())));
        operations.putAll(key, stringMap);
    }

    public <T> void put(String key, String hashKey, T value) {
        if (value == null) return;
        operations.put(key, hashKey, JSONUtil.toJSON(value));
    }

    public <T> Boolean putIfAbsent(String key, String hashKey, T value) {
        if (value == null) return false;
        return operations.putIfAbsent(key, hashKey, JSONUtil.toJSON(value));
    }

    public <T> List<T> values(String key, Class<T> clazz) {
        List<String> values = operations.values(key);
        return values.stream().map(s -> JSONUtil.toBean(s, clazz)).collect(Collectors.toList());
    }

    public <T> Map<String, T> entries(String key, Class<T> valueClazz) {
        return operations.entries(key).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, s -> JSONUtil.toBean(s.getValue(), valueClazz)));
    }

    public <T> Cursor<Map.Entry<String, String>> scan(String key, ScanOptions options, Class<T> valueClazz) {
        return operations.scan(key, options);
    }

    public RedisOperations<String, ?> getOperations() {
        return this.operations.getOperations();
    }
}


