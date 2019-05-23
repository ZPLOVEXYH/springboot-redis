package com.sample.spring.boot.redis.rabbitmq.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送给交换机
 */
public class Senter {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String infoMessage = "hello direct [info]";
            channel.basicPublish(EXCHANGE_NAME, "info", null, infoMessage.getBytes());
            System.out.println("发送消息给到：" + EXCHANGE_NAME + "交换机，路由：info");

            String errorMessage = "hello direct [error]";
            channel.basicPublish(EXCHANGE_NAME, "error", null, errorMessage.getBytes());
            System.out.println("发送消息给到：" + EXCHANGE_NAME + "交换机，路由：error");

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
