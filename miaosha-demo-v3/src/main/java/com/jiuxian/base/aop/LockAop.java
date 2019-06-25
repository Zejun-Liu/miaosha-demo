package com.jiuxian.base.aop;

import com.jiuxian.base.annotation.Lock;
import com.jiuxian.base.util.LockUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-06 13:34:54
 * *
 * @description:
 **/
@Component
@Aspect
public class LockAop {

    @Pointcut(value = "@annotation(lock)", argNames = "lock")
    public void pointcut(Lock lock) {
    }

    @Around(value = "pointcut(lock)", argNames = "joinPoint,lock")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        String lockKey = getLockKey(joinPoint, lock);
        try {
            LockUtil.lock(lockKey);
            return joinPoint.proceed();
        } finally {
            LockUtil.releaseLock(lockKey);
        }
    }

    private String getLockKey(ProceedingJoinPoint joinPoint, Lock lock) {
        if (lock.paramKeyIndex() >= 0) return joinPoint.getArgs()[lock.paramKeyIndex()].toString();

        String lockKey = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(lock.key())) {
                lockKey = joinPoint.getArgs()[i].toString();
                break;
            }
        }
        if (StringUtils.isEmpty(lockKey)) throw new IllegalArgumentException("lock key not found");
        return lockKey;
    }

}
