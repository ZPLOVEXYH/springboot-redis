package com.sample.spring.boot.redis.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        afterLog();
    }

    public void afterLog() {
        System.out.println("结束执行");
    }
}
