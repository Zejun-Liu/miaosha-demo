package com.jiuxian;

import com.jiuxian.base.util.RedisUtil;
import com.jiuxian.user.service.UserService;
import com.jiuxian.user.vo.UserRegisterVo;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Author: LIU ZEJUN
 * Date: 2019-04-22 17:05:00
 * Comment:
 */


public class GenerateTokenAndUser extends MiaoshaDemoV3ApplicationTests {

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

            long qty = random.nextInt(10);
            if (qty <= 0) {
                qty = 1;
            }
            sb.append(token).append(",").append(qty).append("\n");
        }
        writeFile(1, sb.toString());
    }

    @Test
    public void test() {
        for (int i = 0; i < 50000; i++) {
            Random random = new Random();
            RedisUtil.forString.set("a", random.nextInt(10), 60);
        }
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
