package com.jiuxian.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxian.order.entity.Order;

import java.math.BigDecimal;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:04:00
 * Comment:
 */

public interface OrderService extends IService<Order> {

    Long generateOrder(Long userId, Long tmCommodityId, BigDecimal price, Integer qty);
}
