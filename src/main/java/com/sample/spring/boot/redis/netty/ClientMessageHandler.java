package com.sample.spring.boot.redis.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientMessageHandler extends SimpleChannelHandler {

    /**
     * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("Hello world, I'm client.");
    }
}
