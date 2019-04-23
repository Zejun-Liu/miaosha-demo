package com.jiuxian.base.util.redis;

import org.springframework.data.redis.core.GeoOperations;

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
}
