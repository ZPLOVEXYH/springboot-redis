//package com.sample.spring.boot.redis.nio.netty.server;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import org.jboss.netty.channel.ChannelHandlerContext;
//
//public class ServertHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String body = (String) msg;
//        // 前面已经定义了接收为字符串，这里直接接收字符串就可以
//        System.out.println("server"+body);
//        //服务端给客户端的响应
//        // 发送的数据以定义结束的字符串结尾
//        String response= " hi client!$_";
//        // 发送必须还是ByteBuf类型
//        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//}
