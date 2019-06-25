package com.jiuxian.base.resolver;

import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.security.TokenUtil;
import com.jiuxian.user.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:24:00
 * Comment:
 */

public class UserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        assert request != null;

        User currentUser = TokenUtil.getCurrentUser(request);
        if (currentUser == null) throw new BaseException(HttpStatus.UNAUTHORIZED, "登录已过期，请重新登录!");
        return currentUser;
    }
}
