package com.sample.spring.boot.redis.springbootrabbitmq.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfiguration {

    private final static String QUEUE_SIMPLE_NAME = "test_simple_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_SIMPLE_NAME, false, false, false, null);
    }
}
