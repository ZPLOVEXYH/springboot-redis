package com.sample.spring.boot.redis.interceptor;

import com.sample.spring.boot.redis.inter.CacheKeyGenerator;
import com.sample.spring.boot.redis.inter.CacheLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * redis方案
 */
@Aspect
@Configuration
public class LockMethodInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(com.sample.spring.boot.redis.inter.CacheLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        final CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(cacheLock.prefix())) {
            throw new RuntimeException("redis key can't be null...");
        }

        // redis加锁key
        final String lockKey = cacheKeyGenerator.getLockKey(joinPoint);
        try {
            // key不存在才能设置成功，如果此key在redis中存在，那么返回false，否则返回true
            boolean bool = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "");
            if (bool) {
                stringRedisTemplate.expire(lockKey, cacheLock.expire(), cacheLock.timeUnit());
            } else {
                throw new RuntimeException("请勿重复请求");
            }

            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } finally {
            // 如果演示的需要注释改代码，实际应该放开，以免造成死锁的情况产生
//            stringRedisTemplate.delete(lockKey);
        }

        return null;
    }
}
