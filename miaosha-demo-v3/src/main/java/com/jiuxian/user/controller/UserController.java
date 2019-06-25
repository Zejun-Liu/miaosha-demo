package com.jiuxian.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiuxian.base.web.BaseController;
import com.jiuxian.user.entity.User;
import com.jiuxian.user.service.UserService;
import com.jiuxian.user.vo.UserLoginVo;
import com.jiuxian.user.vo.UserRegisterVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 14:22:00
 * Comment:
 */

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterVo userLoginVo) {
        String token = userService.register(userLoginVo);
        return super.success(token);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginVo userLoginVo) {
        String token = userService.login(userLoginVo.getAccount(), userLoginVo.getPassword());
        return super.success(token);
    }


    public void test() {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
        queryWrapper.eq(User::getAccount, "1");

    }
}
