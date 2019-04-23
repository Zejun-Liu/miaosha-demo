package com.jiuxian.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxian.commodity.entity.SeckillCommodity;
import com.jiuxian.order.entity.SeckillOrder;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:06:00
 * Comment:
 */

public interface SeckillOrderService extends IService<SeckillOrder> {

    String getSeckillSign(String tbSeckillCommodityId);

    Long createOrder(Long userId, SeckillCommodity seckillCommodity);

    Long verifyDataAndCreateOrder(Long id, Long seckillCommodityId);

}
