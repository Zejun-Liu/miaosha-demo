package com.jiuxian.order.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 15:42:00
 * Comment:
 */

@Data
public class SeckillVo {

    @NotNull
    private Long tbSeckillCommodityId;
}
