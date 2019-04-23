package com.jiuxian.base.security;

import com.jiuxian.base.annotation.AccessLimit;
import com.jiuxian.base.security.TokenUtil;
import com.jiuxian.base.security.UserContextHandler;
import com.jiuxian.base.util.ActionUtil;
import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.base.web.RestResponse;
import com.jiuxian.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:21:00
 * Comment:
 */
public class AuthorizationIntercept extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = TokenUtil.getCurrentUser(request);
        if (handler instanceof HandlerMethod) {
            AccessLimit accessLimit = ((HandlerMethod) handler).getMethodAnnotation(AccessLimit.class);
            return handlerAccessLimit(request, response, user, accessLimit);
        } else if (user == null) {
            ActionUtil.handleResponse(response, new RestResponse(HttpStatus.UNAUTHORIZED, "请重新登录!"));
            return false;
        }
        return super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHandler.remove();
        TokenUtil.refresh(request);
        super.afterCompletion(request, response, handler, ex);
    }


    private boolean handlerAccessLimit(HttpServletRequest request, HttpServletResponse response, User user, AccessLimit accessLimit) {
        if (accessLimit == null) {
            ActionUtil.handleResponse(response, new RestResponse(HttpStatus.UNAUTHORIZED, "请重新登录!"));
            return false;
        }
        String key = request.getRequestURI();
        if (accessLimit.needLogin()) {
            if (user == null) {
                ActionUtil.handleResponse(response, new RestResponse(HttpStatus.UNAUTHORIZED, "请重新登录!"));
                return false;
            }
            UserContextHandler.set(user);
            key = key + ":" + user.getId();
        }

        Integer count = RedisUtil.forString.get(key, Integer.class);
        if (count == null) {
            RedisUtil.forString.set(key, 1, accessLimit.seconds());
        } else if (count + 1 <= accessLimit.maxCount()) {
            RedisUtil.forString.increment(key);
        } else {
            ActionUtil.handleResponse(response, new RestResponse(HttpStatus.TOO_MANY_REQUESTS, "操作过于频繁请稍后再试!"));
            return false;
        }
        return true;
    }
}
