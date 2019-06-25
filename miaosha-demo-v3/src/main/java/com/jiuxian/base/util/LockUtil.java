package com.jiuxian.base.util;

import com.jiuxian.base.util.lock.LockService;
import com.jiuxian.base.web.WebContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-05 17:36:13
 * *
 * @description:
 **/
@Slf4j
public class LockUtil {

    private final static String LOCK_PREV = "LOCK:";

    private final static long LOCK_EXPIRE = 5000;

    @SuppressWarnings("unchecked")
    private final static LockService<Long> lockService = WebContextHolder.getApplicationContext().getBean(LockService.class);

    public static void lock(String key) {
        key = LOCK_PREV + key;
        long start = System.currentTimeMillis();

        log.info(key + ":竞争锁");
        for (; ; ) {
            boolean success = lockService.setIfAbsent(key, System.currentTimeMillis());
            if (success) break;

            Long timestamp = lockService.get(key);

            if (StringUtils.isEmpty(timestamp)) continue;

            if (getLock(key, timestamp)) break;

            ThreadUtil.sleep(50);
        }
        lockService.expire(key, (int) (LOCK_EXPIRE / 1000));
        log.info(key + ":锁得锁，耗时：" + (System.currentTimeMillis() - start));
    }

    private static boolean getLock(String key, Long timestamp) {
        if (System.currentTimeMillis() - timestamp > LOCK_EXPIRE) {
            Long oldTimestamp = lockService.getAndSet(key, System.currentTimeMillis());
            return (timestamp).equals(oldTimestamp);
        }
        return false;
    }


    public static void releaseLock(String key) {

        lockService.release(LOCK_PREV + key);

        log.info(key + " :释放锁");
    }
}
