package com.sample.spring.boot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @Test
//    public void contextLoads() {
//        String message = "hello rabbitmq!!!";
//        amqpTemplate.convertAndSend("test_simple_queue", message);
//        System.out.println("发送消息" + message + "，ok");
//    }

//    @Test
//    public void testWorkQueue(){
//        for (int i = 0; i < 30; i++) {
//            String message = "hello work queue rabbitmq " + i;
//            amqpTemplate.convertAndSend("test_work_queue", message);
//            System.out.println("发送的消息内容为：" + message);
//        }
//    }

//    @Test
//    public void testFanoutExchange(){
//        String message = "hello fanout exchange";
//        amqpTemplate.convertAndSend("exchange_fanout", "", message);
//        System.out.println("发送的消息内容为：" + message);
//    }

    @Test
    public void testRouteExchange() {
        String infoMessage = "hello route exchange info";
        amqpTemplate.convertAndSend("exchange_route_name", "info", infoMessage);
        System.out.println("发送的内容为：" + infoMessage);

        String errorMessage = "hello route exchange debug";
        amqpTemplate.convertAndSend("exchange_route_name", "debug", errorMessage);
        System.out.println("发送的内容为：" + errorMessage);

    }
}
