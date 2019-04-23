package com.jiuxian.commodity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuxian.commodity.entity.SeckillCommodity;
import org.apache.ibatis.annotations.Update;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:09:00
 * Comment:
 */

public interface SeckillCommodityMapper extends BaseMapper<SeckillCommodity> {

    @Update(value = "update tb_seckill_commodity set last_seckill_stock = last_seckill_stock -1 " +
            "where last_seckill_stock>0 and id=#{tbSeckillCommodityId}")
    int reduceStock(Long tbSeckillCommodityId);

}
