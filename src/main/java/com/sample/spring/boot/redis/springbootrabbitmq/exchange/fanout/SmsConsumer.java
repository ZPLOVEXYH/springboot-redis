//package com.sample.spring.boot.redis.springbootrabbitmq.exchange.fanout;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@RabbitListener(queues = "queue_sms_name")
//@Component
//public class SmsConsumer {
//
//    @RabbitHandler
//    public void smsConsumer(String message) {
//        System.out.println("sms获取得到的消息内容为：" + message);
//    }
//}
