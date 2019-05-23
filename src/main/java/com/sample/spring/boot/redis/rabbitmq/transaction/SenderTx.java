package com.sample.spring.boot.redis.rabbitmq.transaction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SenderTx {

    private final static String QUEUE_NAME = "test_tx_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取得到消息队列的连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取得到消息通道
        Channel channel = connection.createChannel();
        try {
            // 开启事务
            channel.txSelect();
            // 声明一个消息队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 设置发送消息的内容
            String message = "hello transaction rabbitmq";
            // 创建一个消息发送者
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("发送消息内容为:" + message);
            int i = 1 / 0;

            channel.txCommit();

            channel.close();
            connection.close();
        } catch (Exception e) {
            try {
                // 消息队列事务回滚
                channel.txRollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
