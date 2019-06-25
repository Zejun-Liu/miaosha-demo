package com.jiuxian.base.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-25 14:41:20
 * *
 * @description:
 **/
@Slf4j
public abstract class ThreadUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(ExceptionUtil.getStackTrace(e));
        }
    }
}
