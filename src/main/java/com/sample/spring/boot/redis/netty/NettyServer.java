package com.sample.spring.boot.redis.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * netty服务端类
 */
public class NettyServer {

    public static void main(String[] args) {
        // boss线程，主要监听端口和获取worker线程及分配socketChannel给worker线程
        ExecutorService boss = Executors.newCachedThreadPool();
        // worker线程负责数据读写
        ExecutorService worker = Executors.newCachedThreadPool();
        // 设置niosocket工厂
        NioServerSocketChannelFactory nioServerSocketChannelFactory = new NioServerSocketChannelFactory(boss, worker);
        // 服务类
        ServerBootstrap serverBootstrap = new ServerBootstrap(nioServerSocketChannelFactory);
        // 设置管道的工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 管道过滤器
                pipeline.addLast("delimiterBased", new DelimiterBasedFrameDecoder(1024, ChannelBuffers.copiedBuffer("#@#".getBytes())));
                pipeline.addLast("1", new StringDecoder());
                pipeline.addLast("2", new ServerMessageHandler());

                return pipeline;
            }
        });

        // 服务类绑定端口，开放7777，port供client訪问。
        serverBootstrap.bind(new InetSocketAddress(9999));

        System.out.println("服务端启动...");
    }
}

