package com.sample.spring.boot.redis.nio.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    private static Bootstrap bootstrap = new Bootstrap();

    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    public static void main(String[] args) throws InterruptedException {
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientFilter());

        Channel cl = bootstrap.connect("127.0.0.1", 9999).sync().channel();

        String message = "客户端发送数据";
        cl.writeAndFlush(message);

        System.out.println("客户端发送数据：" + message);
    }
}
