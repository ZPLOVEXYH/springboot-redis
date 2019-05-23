package com.sample.spring.boot.redis.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ连接工具类
 */
public class ConnectionUtils {

    private final static String host = "192.168.176.134";

    private final static int port = 5672;

    /**
     * 获取RabbitMQ Connection连接
     *
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        /*
         * 1 创建连接工厂
         * 2 配置共创config
         * 3 获取连接
         * 4 获取信道
         * 5 从信道声明queue
         * 6 发送消息
         * 7 释放资源
         */
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
//        connectionFactory.setVirtualHost("test_exchange_fanout");

        return connectionFactory.newConnection();
    }

}
