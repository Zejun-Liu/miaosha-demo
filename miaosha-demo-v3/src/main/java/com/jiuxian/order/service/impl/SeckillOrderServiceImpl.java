package com.jiuxian.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.rediskey.SeckillOrderKey;
import com.jiuxian.base.security.UserContextHandler;
import com.jiuxian.base.util.EncryptUtil;
import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.commodity.entity.Commodity;
import com.jiuxian.commodity.service.CommodityService;
import com.jiuxian.commodity.service.SeckillCommodityService;
import com.jiuxian.order.entity.SeckillOrder;
import com.jiuxian.order.mapper.SeckillOrderMapper;
import com.jiuxian.order.service.OrderService;
import com.jiuxian.order.service.SeckillOrderService;
import com.jiuxian.stock.service.StockLockService;
import com.jiuxian.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:07:00
 * Comment:
 */

@Slf4j
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Resource
    private OrderService orderService;

    @Resource
    private SeckillCommodityService seckillCommodityService;

    @Resource
    private StockLockService stockLockService;

    @Resource
    private CommodityService commodityService;

    @Override
    public String getSeckillSign(String tbSeckillCommodityId) {
        User user = UserContextHandler.get();
        String newSalt = EncryptUtil.getNewSalt();
        SeckillOrderKey signKey = SeckillOrderKey.signKey;
        RedisUtil.forString.set(currentModelClass() + ":" + user.getId(), newSalt, signKey.expireSeconds());
        return newSalt;
    }


    @Override
    @Transactional
    public Long createOrder(Long userId, Commodity commodity, Integer qty) {
        //5 生成订单
        return this.generateOrder(userId, commodity, qty);
    }

    @Override
    public Long verifyDataAndCreateOrder(Long userId, Long seckillCommodityId, Integer qty) {
        boolean hasStock = stockLockService.doLockOrderStock(seckillCommodityId, qty, 15);
        if (!hasStock) throw new BaseException("商品数量不足");
        Commodity commodity = commodityService.getById(seckillCommodityId);
        return this.createOrder(userId, commodity, qty);
    }


    private Long generateOrder(Long userId, Commodity seckillCommodity, Integer qty) {
/*
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setCreateTime(LocalDateTime.now());
        seckillOrder.setPrice(seckillCommodity.getPrice());
        seckillOrder.setTmSeckillCommodityId(seckillCommodity.getId());
        seckillOrder.setTsUserId(userId);
        super.save(seckillOrder);
*/

        return orderService.generateOrder(userId, seckillCommodity.getId(), seckillCommodity.getPrice(), qty);
    }
}
