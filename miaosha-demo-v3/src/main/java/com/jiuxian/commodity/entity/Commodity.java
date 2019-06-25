package com.jiuxian.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiuxian.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 10:58:00
 * Comment:
 */
@EqualsAndHashCode(callSuper = true)
@TableName("tm_commodity")
@Data
public class Commodity extends BaseEntity {
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
}
