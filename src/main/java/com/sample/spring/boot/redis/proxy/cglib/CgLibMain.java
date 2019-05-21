package com.sample.spring.boot.redis.proxy.cglib;

import com.sample.spring.boot.redis.proxy.IUserServ;
import com.sample.spring.boot.redis.proxy.IUserServImpl;

public class CgLibMain {

    public static void main(String[] args) {
        CgLibTest cgLibTest = new CgLibTest();
        IUserServ iUserServ = (IUserServ) cgLibTest.testCgLib(new IUserServImpl());
        iUserServ.findAllUser();


        IUserServ iUserServ1 = new IUserServImpl();
        iUserServ1.findAllUser();
    }
}
