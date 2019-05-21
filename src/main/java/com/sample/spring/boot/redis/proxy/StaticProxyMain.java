package com.sample.spring.boot.redis.proxy;

public class StaticProxyMain {

    public static void main(String[] args) {
        IUserServ iUserServ = new IUserProxyImpl(new IUserServImpl());
        iUserServ.findAllUser();
    }
}
