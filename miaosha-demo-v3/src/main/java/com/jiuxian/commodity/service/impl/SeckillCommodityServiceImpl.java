package com.jiuxian.commodity.service.impl;

import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.service.impl.BaseServiceImpl;
import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.commodity.entity.SeckillCommodity;
import com.jiuxian.commodity.mapper.SeckillCommodityMapper;
import com.jiuxian.commodity.service.SeckillCommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:09:00
 * Comment:
 */

@Slf4j
@Service
public class SeckillCommodityServiceImpl extends BaseServiceImpl<SeckillCommodityMapper, SeckillCommodity> implements SeckillCommodityService {

    @Resource
    private SeckillCommodityMapper mapper;

    @Override
    public SeckillCommodity getById(Serializable id) {
        String key = super.currentModelClass().getSimpleName() + ":" + id;
        SeckillCommodity seckillCommodity = RedisUtil.forString.get(key, super.currentModelClass());

        if (seckillCommodity != null) return seckillCommodity;

        seckillCommodity = super.getById(id);
        if (seckillCommodity != null) RedisUtil.forString.set(key, seckillCommodity, ENTITY_EXPIRE_TIME);

        return seckillCommodity;
    }

    @Override
    public void verifySeckillTime(SeckillCommodity seckillCommodity) {
        if (seckillCommodity == null) {
            throw new BaseException("秒杀商品有误!");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(seckillCommodity.getStartTime())) throw new BaseException("秒杀时间未到秒杀时间");
        if (seckillCommodity.getEndTime().isBefore(now)) throw new BaseException("秒杀已结束");
    }

    @Override
    public boolean verifyStock(SeckillCommodity seckillCommodity) {
        return seckillCommodity.getLastSeckillStock() > 0;
    }

    @Override
    public boolean reduceStock(Long tbSeckillCommodityId) {
        boolean success = mapper.reduceStock(tbSeckillCommodityId) > 0;
        RedisUtil.delete(super.currentModelClass().getSimpleName() + ":" + tbSeckillCommodityId);
        return success;
    }
}
