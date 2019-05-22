package com.sample.spring.boot.redis.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作模式
 */
public class Sender {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) {
        try {
            // 获取得到连接
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息渠道
            Channel channel = connection.createChannel();
            // 声明一个消息队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 发送的消息内容
            for (int i = 0; i < 5; i++) {
                String message = "hello rabbitmq" + i;
                // 创建一个消息发送者
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("[X]Sent'" + message + "'");
            }

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
