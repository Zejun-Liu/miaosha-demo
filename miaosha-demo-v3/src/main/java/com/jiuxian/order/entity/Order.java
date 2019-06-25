package com.jiuxian.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiuxian.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:03:00
 * Comment:
 */

@EqualsAndHashCode(callSuper = true)
@TableName("to_order")
@Data
public class Order extends BaseEntity {
    private Long tsUserId;
    private Long tmCommodityId;
    private BigDecimal price;
    private Integer qty;
    private LocalDateTime createTime;
}
