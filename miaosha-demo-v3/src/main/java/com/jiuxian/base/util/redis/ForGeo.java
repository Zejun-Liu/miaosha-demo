package com.jiuxian.base.util.redis;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;

import java.util.List;
import java.util.Map;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-23 16:05:00
 * Comment:
 */


public class ForGeo {

    private GeoOperations<String, String> operations;

    public ForGeo(GeoOperations<String, String> operations) {
        this.operations = operations;
    }


    public Long add(String key, Point point, String member) {
        return operations.add(key, point, member);
    }


    public Long add(String key, RedisGeoCommands.GeoLocation<String> location) {
        return operations.add(key, location);
    }


    public Long add(String key, Map<String, Point> memberCoordinateMap) {
        return operations.add(key, memberCoordinateMap);
    }


    public Long add(String key, Iterable<RedisGeoCommands.GeoLocation<String>> geoLocations) {
        return operations.add(key, geoLocations);
    }


    public Distance distance(String key, String member1, String member2) {
        return operations.distance(key, member1, member2);
    }


    public Distance distance(String key, String member1, String member2, Metric metric) {
        return operations.distance(key, member1, member2, metric);
    }


    public List<String> hash(String key, String... members) {
        return operations.hash(key, members);
    }


    public List<Point> position(String key, String... members) {
        return operations.position(key, members);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(String key, Circle within) {
        return operations.radius(key, within);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(String key, Circle within, RedisGeoCommands.GeoRadiusCommandArgs args) {
        return operations.radius(key, within, args);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(String key, String member, double radius) {
        return operations.radius(key, member, radius);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(String key, String member, Distance distance) {
        return operations.radius(key, member, distance);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<String>> radius(String key, String member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args) {
        return operations.radius(key, member, distance, args);
    }


    public Long remove(String key, String... members) {
        return operations.remove(key, members);
    }
}
