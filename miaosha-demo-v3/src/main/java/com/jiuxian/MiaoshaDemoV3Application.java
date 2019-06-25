package com.jiuxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
public class MiaoshaDemoV3Application {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaDemoV3Application.class, args);
    }

}
