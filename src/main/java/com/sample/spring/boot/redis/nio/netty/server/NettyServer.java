package com.sample.spring.boot.redis.nio.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    // 设置服务端端口
    private final static int port = 9999;

    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    private static ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是	ServerBootstrap。
     **/
    public static void main(String[] args) throws InterruptedException {
        try {
            serverBootstrap
                    .group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置服务端的过滤器
                    .childHandler(new NettyServerFilter());

            System.out.println("服务端启动成功,端口是:" + port);
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 获取 Channel 的  CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            eventLoopGroup.shutdownGracefully();
        }
    }
}
