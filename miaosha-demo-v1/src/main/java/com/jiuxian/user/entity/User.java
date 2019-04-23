package com.jiuxian.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiuxian.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:10:00
 * Comment:
 */

@EqualsAndHashCode(callSuper = true)
@TableName(value = "ts_user")
@Data
public class User extends BaseEntity {
    private String account;
    private String name;
    private String password;
    private String salt;
}
