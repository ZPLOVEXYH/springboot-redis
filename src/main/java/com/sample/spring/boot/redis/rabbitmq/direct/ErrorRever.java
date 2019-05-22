package com.sample.spring.boot.redis.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ErrorRever {

    // 交换机名称
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    // 消息队列名称
    private final static String QUEUE_NAME = "test_queue_error";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // queue, exchange, routingKey
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
            channel.basicQos(1);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String str = new String(body);
                    System.out.println("路由info获取得到的消息内容为：" + str);
                }
            };

            // queue, callback
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
