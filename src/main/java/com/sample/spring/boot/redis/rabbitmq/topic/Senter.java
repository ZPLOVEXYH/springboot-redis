package com.sample.spring.boot.redis.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Senter {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) {
        try {
            // 获取得到rabbitmq队列连接
            Connection connection = ConnectionUtils.getConnection();
            // 获取得到通道信息
            Channel channel = connection.createChannel();
            // String exchange, String type
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            // 发送消息内容
            String message = "hello exchange topic!!!";
            // String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish(EXCHANGE_NAME, "quick.orange.rabbit", null, message.getBytes());
            System.out.println("发送的消息内容为：" + message);

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
