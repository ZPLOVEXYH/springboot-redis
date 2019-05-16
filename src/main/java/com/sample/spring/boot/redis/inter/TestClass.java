package com.sample.spring.boot.redis.inter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClass {

    @MyBefore
    public void init(){
        log.info("before。。。");
    }

    @MyTest
    public void test(){
        log.info("save。。。");
    }

    @MyTest
    public void testDelete(){
        log.info("delete。。。");
    }

    @MyAfter
    public void after(){
        log.info("after。。。");
    }
}
