package com.sample.spring.boot.redis.rabbitmq.work;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 轮询分发
 */
public class Customer2 {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) {
        try {
            // 创建消息连接
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息通道
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body);
                    System.out.println("消费者2接收到的消息内容为：" + s);

                    try {
                        // 消息处理时间需要1s时间
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            channel.basicConsume(QUEUE_NAME, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
