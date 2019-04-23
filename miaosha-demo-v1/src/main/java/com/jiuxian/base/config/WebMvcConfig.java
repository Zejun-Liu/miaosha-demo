package com.jiuxian.base.config;

import com.jiuxian.base.security.AuthorizationIntercept;
import com.jiuxian.base.resolver.UserResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:22:00
 * Comment:
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationIntercept())
                .excludePathPatterns("/error", "/user/login", "/user/register")
                .addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userResolver());
    }

    @Bean
    public AuthorizationIntercept authorizationIntercept() {
        return new AuthorizationIntercept();
    }

    @Bean
    public UserResolver userResolver() {
        return new UserResolver();
    }

}
