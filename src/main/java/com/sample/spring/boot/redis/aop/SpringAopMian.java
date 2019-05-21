package com.sample.spring.boot.redis.aop;

import com.sample.spring.boot.redis.proxy.IUserServ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopMian {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        IUserServ iUserServ = (IUserServ) applicationContext.getBean("iUserServ");
        iUserServ.findAllUser();
    }
}
