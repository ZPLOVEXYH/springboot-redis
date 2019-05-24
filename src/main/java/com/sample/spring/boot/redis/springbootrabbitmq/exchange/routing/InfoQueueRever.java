package com.sample.spring.boot.redis.springbootrabbitmq.exchange.routing;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue_info_name")
@Component
public class InfoQueueRever {

    @RabbitHandler
    public void processRoute(String message) {
        System.out.println("info接收到的消息内容为：" + message);
    }

}
