package com.sample.spring.boot.redis.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 批量confirm模式：每发送一批消息后，调用waitForConfirms()方法，等待服务器端confirm。
 */
public class ConfirmSenter2 {

    private final static String QUEUE_NAME = "test_confirm_queue2";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();

            // 开始确认
            channel.confirmSelect();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 0; i < 10; i++) {
                String message = "hello confirm rabbitmq!!!";
                if (i == 5) {
                    int j = 3 / 0;
                }
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }

            if (channel.waitForConfirms()) {
                System.out.println("消息内容，success!!!");
            } else {
                System.out.println("消息内容，fail!!!");
            }

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
