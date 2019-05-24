package com.sample.spring.boot.redis.springbootrabbitmq.exchange.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue_email_name")
@Component
public class EmailConsumer {

    @RabbitHandler
    public void processEmail(String message) {
        System.out.println("email收到的消息内容为：" + message);
    }
}
