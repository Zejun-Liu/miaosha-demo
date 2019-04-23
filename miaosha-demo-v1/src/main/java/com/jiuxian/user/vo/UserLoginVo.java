package com.jiuxian.user.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 14:25:00
 * Comment:
 */

@Data
public class UserLoginVo {

    @NotNull
    private String account;

    @NotNull
    private String password;
}
