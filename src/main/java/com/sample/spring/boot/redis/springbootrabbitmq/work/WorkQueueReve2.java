package com.sample.spring.boot.redis.springbootrabbitmq.work;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "test_work_queue")
@Component
public class WorkQueueReve2 {

    @RabbitHandler
    public void reivce(String message) {
        System.out.println("2、接收到的消息内容为：" + message);
    }
}
