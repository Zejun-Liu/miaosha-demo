package com.jiuxian.stock.entity;

import com.jiuxian.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-05 15:31:52
 * *
 * @description:
 **/
@Getter
@Setter
public class StockBalance extends BaseEntity {

    private Long tmCommodityId;

    private Integer qty;
}
