package com.jiuxian.base.security;

import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.user.entity.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:29:00
 * Comment:
 */
public class TokenUtil {

    private static final String REQUEST_TOKEN = "token";
    private static final int TOKEN_EXPIRE_TIME_MIN = 30;

    private static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String login(User user) {
        String token = generateToken();
        RedisUtil.forString.set(token, user, TOKEN_EXPIRE_TIME_MIN * 60);
        return token;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        String header = request.getHeader(REQUEST_TOKEN);
        if (StringUtils.isEmpty(header)) return null;
        return RedisUtil.forString.get(header, User.class);
    }


    public static void refresh(HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        if (currentUser == null) return;
        RedisUtil.forString.set(request.getHeader(REQUEST_TOKEN), currentUser, TOKEN_EXPIRE_TIME_MIN * 60);
    }

}
