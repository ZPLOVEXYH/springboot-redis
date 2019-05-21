package com.sample.spring.boot.redis.aop;

public class LogAdvice {

    public void beforeMethod() {
        System.out.println("开始执行。。。");
    }

    public void afterMethod() {
        System.out.println("结束执行。。。");
    }
}
