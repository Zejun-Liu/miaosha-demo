package com.jiuxian.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.commodity.entity.Commodity;
import com.jiuxian.commodity.service.CommodityService;
import com.jiuxian.order.entity.Order;
import com.jiuxian.order.mapper.OrderMapper;
import com.jiuxian.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:05:00
 * Comment:
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Long generateOrder(Long userId, Long tmCommodityId, BigDecimal price, Integer qty) {
        Order order = new Order();
        order.setCreateTime(LocalDateTime.now());
        order.setPrice(price);
        order.setTmCommodityId(tmCommodityId);
        order.setTsUserId(userId);
        order.setQty(qty);
        super.save(order);
        return order.getId();
    }
}
