package com.sample.spring.boot.redis.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sample.spring.boot.redis.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列模型--消息生产者
 */
public class Sender {

    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) {
        try {
            // 获取rabbitmq连接
            Connection connection = ConnectionUtils.getConnection();
            // 创建消息渠道
            Channel channel = connection.createChannel();
            // 申明一个队列，没有就会创建
            // queue String类型,表示声明的queue对列的名字
            // durable Boolean类型,表示是否持久化
            // exclusive Boolean类型:当前声明的queue是否专注;true当前连接创建的
            // 任何channle都可以连接这个queue,false,新的channel不可使用
            // autoDelete Boolean类型:在最后连接使用完成后,是否删除队列,false
            // arguments Map类型,其他声明参数
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 准备发送的消息内容
            String message = "hello world";
            // 发送消息
            // exchange String类型,交换机名称,简单模式使用默认交换""
            // routingkey String类型,当前的消息绑定的routingkey,简单模式下,与队列同名即可
            // props BasicProperties类型,消息的属性字段对象,例如BasicProperties
            // 可以设置一个deliveryMode的值0 持久化,1 表示不持久化,durable配合使用
            // body byte[] :消息字符串的byte数组

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("[X]Sent " + message + "");

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
