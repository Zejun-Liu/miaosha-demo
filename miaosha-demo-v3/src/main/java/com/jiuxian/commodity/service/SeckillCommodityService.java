package com.jiuxian.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxian.commodity.entity.SeckillCommodity;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:09:00
 * Comment:
 */

public interface SeckillCommodityService extends IService<SeckillCommodity> {
    void verifySeckillTime(SeckillCommodity seckillCommodity);

    boolean verifyStock(SeckillCommodity seckillCommodity);

    boolean reduceStock(Long tbSeckillCommodityId);
}
