package com.jiuxian.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxian.commodity.entity.Commodity;
import com.jiuxian.order.entity.SeckillOrder;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:06:00
 * Comment:
 */

public interface SeckillOrderService extends IService<SeckillOrder> {

    String getSeckillSign(String tbSeckillCommodityId);

    Long createOrder(Long userId, Commodity seckillCommodity, Integer qty);

    Long verifyDataAndCreateOrder(Long id, Long seckillCommodityId, Integer qty);

}
