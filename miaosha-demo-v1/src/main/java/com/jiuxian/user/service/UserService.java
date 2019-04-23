package com.jiuxian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuxian.user.entity.User;
import com.jiuxian.user.vo.UserRegisterVo;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:11:00
 * Comment:
 */

public interface UserService extends IService<User> {

    String register(UserRegisterVo userLoginVo);

    String login(String account, String password);

}
