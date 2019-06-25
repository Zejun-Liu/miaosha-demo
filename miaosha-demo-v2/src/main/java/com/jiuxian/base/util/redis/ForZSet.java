package com.jiuxian.base.util.redis;

import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.Set;

public class ForZSet {

    private ZSetOperations<String, String> operations;

    public ForZSet(ZSetOperations<String, String> operations) {
        this.operations = operations;
    }

    public Boolean add(String key, String value, double score) {
        return operations.add(key, value, score);
    }


    public Long add(String key, Set<ZSetOperations.TypedTuple<String>> typedTuples) {
        return operations.add(key, typedTuples);
    }


    public Long remove(String key, Object... values) {
        return operations.remove(key, values);
    }


    public Double incrementScore(String key, String value, double delta) {
        return operations.incrementScore(key, value, delta);
    }


    public Long rank(String key, String member) {
        return operations.rank(key, member);
    }


    public Long reverseRank(String key, String member) {
        return operations.remove(key, member);
    }


    public Set<String> range(String key, long start, long end) {
        return operations.range(key, start, end);
    }


    public Set<ZSetOperations.TypedTuple<String>> rangeWithScores(String key, long start, long end) {
        return operations.rangeWithScores(key, start, end);
    }


    public Set<String> rangeByScore(String key, double min, double max) {
        return operations.rangeByScore(key, min, max);
    }


    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max) {
        return operations.rangeByScoreWithScores(key, min, max);
    }


    public Set<String> rangeByScore(String key, double min, double max, long offset, long count) {
        return operations.rangeByScore(key, min, max, offset, count);
    }


    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(
            String key, double min, double max, long offset, long count) {
        return operations.rangeByScoreWithScores(key, min, max, offset, count);
    }


    public Set<String> reverseRange(String key, long start, long end) {
        return operations.reverseRange(key, start, end);
    }


    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(String key, long start, long end) {
        return operations.reverseRangeWithScores(key, start, end);
    }


    public Set<String> reverseRangeByScore(String key, double min, double max) {
        return operations.reverseRangeByScore(key, min, max);
    }


    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max) {
        return operations.reverseRangeByScoreWithScores(key, min, max);
    }


    public Set<String> reverseRangeByScore(String key, double min, double max, long offset, long count) {
        return operations.reverseRangeByScore(key, min, max, offset, count);
    }


    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(
            String key, double min, double max, long offset, long count) {
        return operations.reverseRangeByScoreWithScores(key, min, max, offset, count);
    }


    public Long count(String key, double min, double max) {
        return operations.count(key, min, max);
    }


    public Long size(String key) {
        return operations.size(key);
    }


    public Long zCard(String key) {
        return operations.zCard(key);
    }


    public Double score(String key, String member) {
        return operations.score(key, member);
    }


    public Long removeRange(String key, long start, long end) {
        return operations.removeRange(key, start, end);
    }


    public Long removeRangeByScore(String key, double min, double max) {
        return operations.removeRangeByScore(key, min, max);
    }


    public Long unionAndStore(String key, String otherKey, String destKey) {
        return operations.unionAndStore(key, otherKey, destKey);
    }


    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return operations.unionAndStore(key, otherKeys, destKey);
    }


    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey,
                              RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return operations.unionAndStore(key, otherKeys, destKey, aggregate, weights);
    }


    public Long intersectAndStore(String key, String otherKey, String destKey) {
        return operations.intersectAndStore(key, otherKey, destKey);
    }


    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return operations.intersectAndStore(key, otherKeys, destKey);
    }


    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey,
                                  RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return operations.intersectAndStore(key, otherKeys, destKey, aggregate, weights);
    }


    public Cursor<ZSetOperations.TypedTuple<String>> scan(String key, ScanOptions options) {
        return operations.scan(key, options);
    }


    public Set<String> rangeByLex(String key, RedisZSetCommands.Range range) {
        return operations.rangeByLex(key, range);
    }


    public Set<String> rangeByLex(String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        return operations.rangeByLex(key, range, limit);
    }


    public RedisOperations<String, String> getOperations() {
        return operations.getOperations();
    }
}