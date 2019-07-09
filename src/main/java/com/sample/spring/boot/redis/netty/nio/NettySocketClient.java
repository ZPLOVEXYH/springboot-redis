package com.sample.spring.boot.redis.netty.nio;

import io.netty.bootstrap.Bootstrap;
import org.springframework.beans.factory.annotation.Autowired;

public class NettySocketClient {

    @Autowired
    Bootstrap clientBootstrap;

    public void connect() {
        try {
            clientBootstrap.connect("192.168.237.1", 9999).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
