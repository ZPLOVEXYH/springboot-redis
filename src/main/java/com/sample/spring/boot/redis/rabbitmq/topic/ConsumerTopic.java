package com.sample.spring.boot.redis.rabbitmq.topic;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTopic {
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    private final static String QUEUE_NAME = "test_queue_topic";


    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // String queue, String exchange, String routingKey
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "*.orange.*");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String str = new String(body);
                    System.out.println("1接收到的消息为：" + str);
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
