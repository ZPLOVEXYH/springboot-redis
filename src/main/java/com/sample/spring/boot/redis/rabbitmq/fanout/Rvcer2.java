package com.sample.spring.boot.redis.rabbitmq.fanout;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Rvcer2 {

    // 交换机的名称
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    // 邮件消息队列的名称
    private final static String QUEUE_NAME = "test_fanout_email";

    public static void main(String[] args) {
        try {
            // 创建消息队列名称
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息渠道
            Channel channel = connection.createChannel();
            // 声明一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 将队列绑定到交换机
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
            // 固定每次发送一条消息给消费方
            channel.basicQos(1);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String str = new String(body);

                    System.out.println("2接收到的消息内容为：" + str);

                    try {
                        Thread.sleep(1000);
                        // 手动应答
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
