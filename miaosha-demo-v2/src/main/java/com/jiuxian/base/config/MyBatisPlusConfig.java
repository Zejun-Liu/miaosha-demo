package com.jiuxian.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 10:53:00
 * Comment:
 */

@Configuration
@MapperScan("com.jiuxian.**.mapper")
public class MyBatisPlusConfig {
}
