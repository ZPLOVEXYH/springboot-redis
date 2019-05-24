package com.sample.spring.boot.redis.springbootrabbitmq.work;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbltmqWorkConfig {

    private final static String WORK_QUEUE_NAME = "test_work_queue";

    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE_NAME, false, false, false, null);
    }
}
