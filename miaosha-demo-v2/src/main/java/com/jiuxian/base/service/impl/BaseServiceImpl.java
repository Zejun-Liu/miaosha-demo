package com.jiuxian.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.base.entity.BaseEntity;
import com.jiuxian.base.service.BaseService;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-23 10:01:00
 * Comment:
 */

public class BaseServiceImpl<M extends BaseMapper, T extends BaseEntity> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

    protected final int ENTITY_EXPIRE_TIME = 7 * 24 * 60 * 60;
}
