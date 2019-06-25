package com.jiuxian.commodity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.commodity.entity.Commodity;
import com.jiuxian.commodity.mapper.CommodityMapper;
import com.jiuxian.commodity.service.CommodityService;
import org.springframework.stereotype.Service;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:02:00
 * Comment:
 */

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
}
