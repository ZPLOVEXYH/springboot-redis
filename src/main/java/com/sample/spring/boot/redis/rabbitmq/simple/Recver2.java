package com.sample.spring.boot.redis.rabbitmq.simple;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列模型--消息消费者2
 */
public class Recver2 {
    private final static String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) {
        try {
            // 获取得到连接
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息渠道
            Channel channel = connection.createChannel();
            // 申明要消费的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 创建一个回调的消费者处理类
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 接收到的消息
                    String message = new String(body);
                    System.out.println(" [x] Received '" + message + "'");
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
