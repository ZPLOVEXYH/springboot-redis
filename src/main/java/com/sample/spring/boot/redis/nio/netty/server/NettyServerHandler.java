package com.sample.spring.boot.redis.nio.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

/**
 * Title: HelloServerHandler
 * Description:  服务端业务逻辑
 * Version:1.0.0
 *
 * @author Administrator
 * @date 2017-8-31
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 收到消息时，返回信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        // 收到消息直接打印输出
        System.out.println("服务端接受的消息 : " + s);
        if ("q".equals(s)) {
            // 服务端断开的条件
            channelHandlerContext.close();
        }

        // 返回客户端消息
        channelHandlerContext.writeAndFlush("收到消息:" + s + ",当前的时间是:" + new Date());
    }

    /**
     * 建立连接时，返回消息
     * channelActive方法在接到来自客户端的连接时触发（这里只是打印了收到的连接的信息），所以在执行telnet命令时，看到控制台会有相应的输出
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        System.out.println("主机名称：" + InetAddress.getLocalHost().getHostName());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + " success !!!");
        super.channelActive(ctx);
    }

}
