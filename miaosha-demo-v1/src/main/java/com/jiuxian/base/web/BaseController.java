package com.jiuxian.base.web;

import org.springframework.http.ResponseEntity;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:56:00
 * Comment:
 */


public abstract class BaseController {
    protected ResponseEntity<?> success() {
        return this.success(null);
    }

    protected <T> ResponseEntity<RestResponse<T>> success(T data) {
        RestResponse<T> response = new RestResponse<>(data);
        return ResponseEntity.ok(response);
    }
}
