package com.sample.spring.boot.redis.inter;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key的生成策略
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数，生成指定缓存key
     *
     * @param joinPoint
     * @return 缓存key
     */
    String getLockKey(ProceedingJoinPoint joinPoint);
}
