package com.sample.spring.boot.redis.rabbitmq.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fanout exchange交换机
 */
public class Senter {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) {
        try {
            // 创建消息队列连接
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息渠道
            Channel channel = connection.createChannel();
            // 声明一个fanout分发交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
//            for(int i = 0; i < 10; i++) {
//                String message = "hello rabbitmq fanout exchange " + i;
//                channel.basicPublish(EXCHANGE_NAME,"", null, message.getBytes());
//
//                System.out.println("exchange fanout发送的消息为：" + message);
//            }

            String message = "hello rabbitmq fanout exchange 1";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("exchange fanout发送的消息为：" + message);

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
