package com.sample.spring.boot.redis.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConfirmSenter {

    private final static String QUEUE_NAME = "test_confirm_queue";

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();

            // 开始确认
            channel.confirmSelect();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello confirm rabbitmq!!!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            if (channel.waitForConfirms()) {
                System.out.println("消息内容：" + message + "，success!!!");
            } else {
                System.out.println("消息内容：" + message + "，fail!!!");
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
