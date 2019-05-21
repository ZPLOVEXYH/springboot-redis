package com.sample.spring.boot.redis.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.transaction.annotation.Transactional;

public class Person implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;

    public Person() {
        System.out.println("调用构造方法为属性值初始化");
    }

    public Person(String nameArg) {
        System.out.println("调用有参数的构造方法：" + nameArg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware 获取beanName id 值 " + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 获取BeanFactory " + beanFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 执行afterPropertiesSet()方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 的destory方法");
    }

    @Transactional
    public void init() {
        System.out.println("init初始化方法。。。");
    }
}
