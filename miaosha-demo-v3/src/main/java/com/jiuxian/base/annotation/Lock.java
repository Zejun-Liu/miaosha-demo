package com.jiuxian.base.annotation;

import java.lang.annotation.*;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-06 13:35:02
 * *
 * @description:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Lock {

    String key();

    int paramKeyIndex() default -1;

}
