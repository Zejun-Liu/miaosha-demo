package com.jiuxian.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Author: liuzejun
 * Date: 2018-11-23 16:02:00
 * Comment:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException implements Serializable {

    private Integer code;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(Integer code) {
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }
}
