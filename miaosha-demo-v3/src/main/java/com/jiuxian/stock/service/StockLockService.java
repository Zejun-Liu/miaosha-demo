package com.jiuxian.stock.service;

import com.jiuxian.base.service.BaseService;
import com.jiuxian.stock.entity.StockLock;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-05 15:56:39
 * *
 * @description:
 **/
public interface StockLockService extends BaseService<StockLock> {

    boolean doLockOrderStock(Long commodityId, Integer qty, int expireTimeMin);

}
