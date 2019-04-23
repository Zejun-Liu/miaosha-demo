package com.jiuxian;

import com.jiuxian.user.service.UserService;
import com.jiuxian.user.vo.UserRegisterVo;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 17:05:00
 * Comment:
 */


public class GenerateTokenAndUser extends MiaoshaDemoV1ApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void generate() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 5000; i++) {
            UserRegisterVo vo = new UserRegisterVo();
            Random random = new Random();
            vo.setAccount(random.nextLong() + "");
            vo.setName(random.nextLong() + "");
            vo.setPassword("123456");
            String token = userService.register(vo);
            sb.append(token).append("\n");
        }
        writeFile(1, sb.toString());
    }

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Test
    public void test() {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        operations.set("a", 1, 5, TimeUnit.SECONDS);
    }


    private void writeFile(int i, String str) {
        File file = new File("D://" + i + ".txt");
        try {
            FileUtils.writeStringToFile(file, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
