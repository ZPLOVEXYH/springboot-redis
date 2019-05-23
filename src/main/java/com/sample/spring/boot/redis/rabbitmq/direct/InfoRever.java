package com.sample.spring.boot.redis.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class InfoRever {

    // 交换机名称
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    // 消息队列名称
    private final static String QUEUE_NAME = "test_queue_info";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // queue, exchange, routingKey
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "info");
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
            channel.basicQos(1);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String str = new String(body);
                    System.out.println("路由info获取得到的消息内容为：" + str);

                    try {
//                        String s = "0d";
//                        // 此处报错
//                        System.out.println(Integer.parseInt(s));

                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // 接收到消息处理业务逻辑出错的话，那么需要手动通知
                        channel.basicAck(envelope.getDeliveryTag(), false);

                        e.printStackTrace();
                    }
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
