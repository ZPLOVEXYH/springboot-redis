package com.sample.spring.boot.redis.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NettyClient {

    public static void main(String[] args) {
        //
        Executor bossExecutor = Executors.newCachedThreadPool();
        Executor workerExecutor = Executors.newCachedThreadPool();
        NioClientSocketChannelFactory nioClientSocketChannelFactory = new NioClientSocketChannelFactory(bossExecutor, workerExecutor);
        // 客户端
        ClientBootstrap clientBootstrap = new ClientBootstrap(nioClientSocketChannelFactory);
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // 管道过滤器
                pipeline.addLast("delimiterBased", new DelimiterBasedFrameDecoder(1024, ChannelBuffers.copiedBuffer("#@#".getBytes())));
                pipeline.addLast("1", new StringDecoder());
                pipeline.addLast("2", new ClientMessageHandler());

                return pipeline;
            }
        });

        //  连接到本地的7777，port的服务端
        ChannelFuture channelFuture = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 9999));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("成功！！！");
                } else {
                    System.out.println("失败！！！");
                }
            }
        });
    }
}
