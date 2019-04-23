package com.jiuxian.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.rediskey.SeckillOrderKey;
import com.jiuxian.base.security.UserContextHandler;
import com.jiuxian.base.util.EncryptUtil;
import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.commodity.entity.SeckillCommodity;
import com.jiuxian.commodity.service.SeckillCommodityService;
import com.jiuxian.order.entity.SeckillOrder;
import com.jiuxian.order.mapper.SeckillOrderMapper;
import com.jiuxian.order.service.OrderService;
import com.jiuxian.order.service.SeckillOrderService;
import com.jiuxian.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
    public Long createOrder(Long userId, SeckillCommodity seckillCommodity) {
        //4 扣减库存
        boolean success = seckillCommodityService.reduceStock(seckillCommodity.getId());
        if (!success) throw new BaseException("商品数量不足");
        //5 生成订单
        return this.generateOrder(userId, seckillCommodity);
    }

    @Override
    public Long verifyDataAndCreateOrder(Long userId, Long seckillCommodityId) {
        SeckillCommodity seckillCommodity = seckillCommodityService.getById(seckillCommodityId);
        log.info("seckillCommodity:{}", seckillCommodity);
        //1 判断秒杀时间
        seckillCommodityService.verifySeckillTime(seckillCommodity);
        //2 判断库存
        boolean hasStock = seckillCommodityService.verifyStock(seckillCommodity);
        log.info("hasStock:{}", hasStock);

        if (!hasStock) throw new BaseException("商品数量不足");

        //3 每人秒杀数量
        this.verifyUserSeckillQty(userId, seckillCommodity.getId());

        return this.createOrder(userId, seckillCommodity);
    }

    private void verifyUserSeckillQty(Long userId, Long tbSeckillCommodityId) {
        Integer count = super.lambdaQuery().eq(SeckillOrder::getTsUserId, userId)
                .eq(SeckillOrder::getTmSeckillCommodityId, tbSeckillCommodityId)
                .count();
        if (count > 0) {
            throw new BaseException("每人只能秒杀一件!");
        }

    }

    private Long generateOrder(Long userId, SeckillCommodity seckillCommodity) {
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setCreateTime(LocalDateTime.now());
        seckillOrder.setPrice(seckillCommodity.getSeckillPrice());
        seckillOrder.setTmSeckillCommodityId(seckillCommodity.getId());
        seckillOrder.setTsUserId(userId);
        super.save(seckillOrder);

        return orderService.generateOrder(userId, seckillCommodity.getTmCommodityId(), seckillCommodity.getSeckillPrice(), 1);
    }
}
