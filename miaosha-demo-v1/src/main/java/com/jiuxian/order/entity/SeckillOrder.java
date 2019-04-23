package com.jiuxian.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiuxian.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:05:00
 * Comment:
 */

@EqualsAndHashCode(callSuper = true)
@TableName("to_seckill_order")
@Data
public class SeckillOrder extends BaseEntity {
    private Long tsUserId;
    private Long tmSeckillCommodityId;
    private BigDecimal price;
    private LocalDateTime createTime;
}
