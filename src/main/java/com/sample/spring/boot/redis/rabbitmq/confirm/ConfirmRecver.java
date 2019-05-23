package com.sample.spring.boot.redis.rabbitmq.confirm;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 普通confirm模式：每发送一条消息后，调用waitForConfirms()方法，等待服务器端confirm。实际上是一种串行confirm了。
 */
public class ConfirmRecver {

    private final static String QUEUE_NAME = "test_confirm_queue3";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body);
                    System.out.println("接收到的消息内容为：" + s);

                    if (envelope.getDeliveryTag() == 2L) {
                        channel.basicReject(envelope.getDeliveryTag(), true);
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
