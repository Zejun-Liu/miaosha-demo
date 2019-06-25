package com.jiuxian.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.base.exception.BaseException;
import com.jiuxian.base.security.TokenUtil;
import com.jiuxian.base.util.BeanUtil;
import com.jiuxian.base.util.EncryptUtil;
import com.jiuxian.user.entity.User;
import com.jiuxian.user.mapper.UserMapper;
import com.jiuxian.user.service.UserService;
import com.jiuxian.user.vo.UserRegisterVo;
import org.springframework.stereotype.Service;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 11:11:00
 * Comment:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final static int ENCRYPT_COUNT = 2;


    @Override
    public String register(UserRegisterVo userLoginVo) {
        User user = BeanUtil.copy(userLoginVo, new User());
        user.setSalt(EncryptUtil.getNewSalt());
        user.setPassword(EncryptUtil.generatePassword(user.getPassword(), user.getSalt(), ENCRYPT_COUNT));
        super.save(user);
        return TokenUtil.login(user);
    }

    @Override
    public String login(String account, String password) {
        User user = super.lambdaQuery().eq(User::getAccount, account).one();
        if (user == null) throw new BaseException("用户不存在");

        String generatePassword = EncryptUtil.generatePassword(password, user.getSalt(), ENCRYPT_COUNT);
        if (!generatePassword.equals(user.getPassword())) {
            throw new BaseException("密码错误!");
        }
        return TokenUtil.login(user);
    }
}
