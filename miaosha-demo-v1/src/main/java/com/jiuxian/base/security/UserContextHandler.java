package com.jiuxian.base.security;

import com.jiuxian.user.entity.User;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:28:00
 * Comment:
 */


public class UserContextHandler {

    private static ThreadLocal<User> local = new ThreadLocal<>();

    public static void set(User user) {
        local.set(user);
    }

    public static User get() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
