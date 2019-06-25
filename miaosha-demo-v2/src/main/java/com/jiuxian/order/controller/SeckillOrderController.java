package com.jiuxian.order.controller;

import com.jiuxian.base.annotation.AccessLimit;
import com.jiuxian.base.security.UserContextHandler;
import com.jiuxian.base.web.BaseController;
import com.jiuxian.order.service.SeckillOrderService;
import com.jiuxian.order.vo.SeckillVo;
import com.jiuxian.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:17:00
 * Comment:
 */
@RestController
@RequestMapping(value = "/seckill/order")
public class SeckillOrderController extends BaseController {

    @Resource
    private SeckillOrderService seckillOrderService;

    @GetMapping(value = "/getSeckillSign")
    @AccessLimit(seconds = 5, maxCount = 5)
    public ResponseEntity getSeckillSign(@RequestParam String tbSeckillCommodityId) {
        String sign = seckillOrderService.getSeckillSign(tbSeckillCommodityId);
        return super.success(sign);
    }


    @PostMapping(value = "/seckill")
    @AccessLimit(seconds = 5, maxCount = 5)
    public ResponseEntity seckill(@RequestBody @Valid SeckillVo seckillVo) {
        User user = UserContextHandler.get();
        Long orderId = seckillOrderService.verifyDataAndCreateOrder(user.getId(), seckillVo.getTbSeckillCommodityId());
        return super.success(orderId);
    }
}
