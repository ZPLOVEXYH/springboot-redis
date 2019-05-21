package com.sample.spring.boot.redis.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeLog implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        beforeLog();
    }

    public void beforeLog() {
        System.out.println("开始执行");
    }
}
