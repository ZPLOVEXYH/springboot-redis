//package com.sample.spring.boot.redis.nio.netty.server;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
//
///**
// *  1.在上面这个Server.java中，我们都要定义两个线程池，boss和worker，boss是用于管理连接到server端的client的连接数的线程池，而woeker是用于管理实际操作的线程池。
// *       2.ServerBootstrap用一个ServerSocketChannelFactory 来实例化。ServerSocketChannelFactory 有两种选择，
// *       一种是NioServerSocketChannelFactory，
// *       一种是OioServerSocketChannelFactory。 前者使用NIO，后则使用普通的阻塞式IO。它们都需要两个线程池实例作为参数来初始化，一个是boss线程池，一个是worker线程池。
// *       3.然后使ServerBookstrap管理boss和worker线程池。并且设置各个缓冲区的大小。
// *       4.这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel。
// *       也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel 或者其对应的ChannelPipeline来实现你的网络程序。
// *       当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，然后提取这些匿名类到最顶层的类上。
// *       5.在使用原始的encoder、decoder的情况下，Netty发送接收数据都是按照ByteBuf的形式，其它形式都是不合法的。
// *       而在上面这个Socket中，我使用sc.pipeline().addLast()这个方法设置了接收为字符串类型，注意：只能设置接收为字符串类型，发送还是需要发送ByteBuf类型的数据。
// *       而且在这里我还设置了以$_为结尾的字符串就代表了本次请求字符串的结束。
// *       6.通过b.bind绑定端口，用于监听的端口号
// */
//public class Server {
//
//    public static void main(String[] args) throws InterruptedException {
//        // 1.第一个线程组是用于接收Client端连接的
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        // 2.第二个线程组是用于实际的业务处理的
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap b = new ServerBootstrap();
//        // 绑定两个线程池
//        b.group(bossGroup, workerGroup);
//        // 指定NIO的模式，如果是客户端就是NioSocketChannel
//        b.channel(NioServerSocketChannel.class);
//        // TCP的缓冲区设置
//        b.option(ChannelOption.SO_BACKLOG, 1024);
//        // 设置发送缓冲的大小
//        b.option(ChannelOption.SO_SNDBUF, 32*1024);
//        // 设置接收缓冲区大小
//        b.option(ChannelOption.SO_RCVBUF, 32*1024);
//        // 保持连续
//        b.option(ChannelOption.SO_KEEPALIVE, true);
//        b.childHandler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(SocketChannel sc) throws Exception {
//                // 拆包粘包定义结束字符串（第一种解决方案）
//                ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//                // 在管道中加入结束字符串
//                sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
//                // sc.pipeline().addLast(new FixedLengthFrameDecoder(200));第二种定长
//                // 定义接收类型为字符串把ByteBuf转成String
//                sc.pipeline().addLast(new StringDecoder());
//                // 在这里配置具体数据接收方法的处理
//                sc.pipeline().addLast(new ServertHandler());
//            }
//        });
//        // 绑定端口
//        ChannelFuture future = b.bind(8765).sync();
//        // 等待关闭(程序阻塞在这里等待客户端请求)
//        future.channel().closeFuture().sync();
//        // 关闭线程
//        bossGroup.shutdownGracefully();
//        // 关闭线程
//        workerGroup.shutdownGracefully();
//    }
//}
//package com.sample.spring.boot.redis.nio.netty.server;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
//
///**
// *  1.在上面这个Server.java中，我们都要定义两个线程池，boss和worker，boss是用于管理连接到server端的client的连接数的线程池，而woeker是用于管理实际操作的线程池。
// *       2.ServerBootstrap用一个ServerSocketChannelFactory 来实例化。ServerSocketChannelFactory 有两种选择，
// *       一种是NioServerSocketChannelFactory，
// *       一种是OioServerSocketChannelFactory。 前者使用NIO，后则使用普通的阻塞式IO。它们都需要两个线程池实例作为参数来初始化，一个是boss线程池，一个是worker线程池。
// *       3.然后使ServerBookstrap管理boss和worker线程池。并且设置各个缓冲区的大小。
// *       4.这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的Channel。
// *       也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel 或者其对应的ChannelPipeline来实现你的网络程序。
// *       当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，然后提取这些匿名类到最顶层的类上。
// *       5.在使用原始的encoder、decoder的情况下，Netty发送接收数据都是按照ByteBuf的形式，其它形式都是不合法的。
// *       而在上面这个Socket中，我使用sc.pipeline().addLast()这个方法设置了接收为字符串类型，注意：只能设置接收为字符串类型，发送还是需要发送ByteBuf类型的数据。
// *       而且在这里我还设置了以$_为结尾的字符串就代表了本次请求字符串的结束。
// *       6.通过b.bind绑定端口，用于监听的端口号
// */
//public class Server {
//
//    public static void main(String[] args) throws InterruptedException {
//        // 1.第一个线程组是用于接收Client端连接的
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        // 2.第二个线程组是用于实际的业务处理的
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap b = new ServerBootstrap();
//        // 绑定两个线程池
//        b.group(bossGroup, workerGroup);
//        // 指定NIO的模式，如果是客户端就是NioSocketChannel
//        b.channel(NioServerSocketChannel.class);
//        // TCP的缓冲区设置
//        b.option(ChannelOption.SO_BACKLOG, 1024);
//        // 设置发送缓冲的大小
//        b.option(ChannelOption.SO_SNDBUF, 32*1024);
//        // 设置接收缓冲区大小
//        b.option(ChannelOption.SO_RCVBUF, 32*1024);
//        // 保持连续
//        b.option(ChannelOption.SO_KEEPALIVE, true);
//        b.childHandler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(SocketChannel sc) throws Exception {
//                // 拆包粘包定义结束字符串（第一种解决方案）
//                ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//                // 在管道中加入结束字符串
//                sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
//                // sc.pipeline().addLast(new FixedLengthFrameDecoder(200));第二种定长
//                // 定义接收类型为字符串把ByteBuf转成String
//                sc.pipeline().addLast(new StringDecoder());
//                // 在这里配置具体数据接收方法的处理
//                sc.pipeline().addLast(new ServertHandler());
//            }
//        });
//        // 绑定端口
//        ChannelFuture future = b.bind(8765).sync();
//        // 等待关闭(程序阻塞在这里等待客户端请求)
//        future.channel().closeFuture().sync();
//        // 关闭线程
//        bossGroup.shutdownGracefully();
//        // 关闭线程
//        workerGroup.shutdownGracefully();
//    }
//}
