package com.jiuxian.stock.service.impl;

import com.jiuxian.base.annotation.Lock;
import com.jiuxian.base.service.impl.BaseServiceImpl;
import com.jiuxian.stock.entity.StockBalance;
import com.jiuxian.stock.entity.StockLock;
import com.jiuxian.stock.mapper.StockLockMapper;
import com.jiuxian.stock.service.StockBalanceService;
import com.jiuxian.stock.service.StockLockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-05 15:56:56
 * *
 * @description:
 **/
@Service
public class StockLockServiceImpl extends BaseServiceImpl<StockLockMapper, StockLock> implements StockLockService {

    @Resource
    private StockBalanceService stockBalanceService;

    @Lock(key = "commodityId")
    @Transactional
    public boolean doLockOrderStock(Long commodityId, Integer qty, int expireTimeMin) {
        LocalDateTime expireEndTime = LocalDateTime.now().plusMinutes(expireTimeMin);
        boolean stockEnough = checkStock(commodityId, qty);
        if (stockEnough) {
            StockLock stockLock = getStockLock(expireEndTime, commodityId, qty);
            this.save(stockLock);
        }
        return stockEnough;
    }

    private StockLock getStockLock(LocalDateTime expireEndTime, Long commodityId, Integer qty) {
        StockLock lock = new StockLock();
        lock.setQty(qty);
        lock.setTmCommodityId(commodityId);
        lock.setExpireTime(expireEndTime);
        return lock;
    }


    private boolean checkStock(Long commodityId, Integer qty) {

        Map<Long, Integer> availableCommodityItemsQty = getAvailableQty(Collections.singletonList(commodityId));

        if (!availableCommodityItemsQty.containsKey(commodityId)) {
            return false;
        }
        return availableCommodityItemsQty.get(commodityId) - qty >= 0;
    }

    private Map<Long, Integer> getAvailableQty(List<Long> comItemIds) {
        List<StockBalance> stockBalances = stockBalanceService.lambdaQuery().in(StockBalance::getTmCommodityId, comItemIds).list();
        Map<Long, Integer> balanceCommodityIdQtyMap = stockBalances.stream().collect(Collectors.toMap(StockBalance::getTmCommodityId, StockBalance::getQty));
        List<StockLock> stockLocks = super.lambdaQuery().in(StockLock::getTmCommodityId, comItemIds).ge(StockLock::getExpireTime, LocalDateTime.now()).list();
        Map<Long, Integer> lockCommodityIdQtyMap = stockLocks.stream().collect(Collectors.groupingBy(StockLock::getTmCommodityId))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue().stream().mapToInt(StockLock::getQty).sum()));

        balanceCommodityIdQtyMap.forEach((key, value) -> {
            if (lockCommodityIdQtyMap.containsKey(key)) {
                balanceCommodityIdQtyMap.put(key, value - lockCommodityIdQtyMap.get(key));
            }
        });
        return balanceCommodityIdQtyMap;
    }
}
