package com.sample.spring.boot.redis.springbootrabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单模式消费者
 */
@RabbitListener(queues = "test_simple_queue")
@Component
public class SimpleRecv {

    @RabbitHandler
    public void process(String message) {
        System.out.println("接收到的消息内容为：" + message);
    }
}
