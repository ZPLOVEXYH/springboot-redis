package com.sample.spring.boot.redis.bean;

import com.sample.spring.boot.redis.inter.ParentAnnotation;

@ParentAnnotation
public class Parent {

    @ParentAnnotation
    public void test(){

    }
}
