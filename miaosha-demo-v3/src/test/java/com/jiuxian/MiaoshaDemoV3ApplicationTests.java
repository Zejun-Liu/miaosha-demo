package com.jiuxian;

import com.jiuxian.base.util.LockUtil;
import com.jiuxian.base.util.ThreadUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaDemoV3ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLock() {
        ExecutorService pool = Executors.newFixedThreadPool(500);

        for (int i = 0; i < 50000; i++) {
            pool.execute(() -> {
                LockUtil.lock("ABC");
                ThreadUtil.sleep(100);
                LockUtil.releaseLock("ABC");
            });
        }
        ThreadUtil.sleep(1000 * 60);
    }
}

