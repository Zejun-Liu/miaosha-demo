package com.jiuxian.base.config;

import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.web.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 13:02:00
 * Comment:
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public RestResponse baseExceptionHandler(BaseException e) {
        log.error(e.getMessage());
        return new RestResponse<>(e.getCode(), e.getMessage());
    }

}