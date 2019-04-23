package com.jiuxian.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 10:49:00
 * Comment:
 */

@Data
public abstract class BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;
}
