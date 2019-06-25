package com.jiuxian.base.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 15:00:00
 * Comment:
 */

@Component
public class WebContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebContextHolder.applicationContext = applicationContext;
    }
}
