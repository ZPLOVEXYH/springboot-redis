package com.sample.spring.boot.redis.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerMessageHandler extends SimpleChannelHandler {

    /**
     * 当有client绑定到服务端的时候触发，打印"Hello world, I'm server."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.getPipeline().getLast();
        System.out.println("Hello world, I'm server.");
    }
}
