package com.jiuxian.stock.entity;

import com.jiuxian.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-05 15:54:27
 * *
 * @description:
 **/
@Getter
@Setter
public class StockLock extends BaseEntity {

    private Long tmCommodityId;

    private Integer qty;

    private LocalDateTime expireTime;
}
