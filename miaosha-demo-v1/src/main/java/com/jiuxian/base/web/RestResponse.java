package com.jiuxian.base.web;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public RestResponse() {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
    }

    public RestResponse(T data) {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public RestResponse(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResponse build() {
        return new RestResponse();
    }
}
