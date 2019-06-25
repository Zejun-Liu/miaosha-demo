package com.jiuxian.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiuxian.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:08:00
 * Comment:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_seckill_commodity")
public class SeckillCommodity extends BaseEntity {
    private Long tmCommodityId;
    private BigDecimal seckillPrice;
    private Integer seckillStock;
    private Integer lastSeckillStock;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
