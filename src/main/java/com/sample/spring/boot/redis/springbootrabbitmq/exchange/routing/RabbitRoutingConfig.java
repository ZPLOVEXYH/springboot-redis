package com.sample.spring.boot.redis.springbootrabbitmq.exchange.routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitRoutingConfig {

    private final static String EXCHANGE_ROUTE_NAME = "exchange_route_name";

    private final static String QUEUE_INFO_NAME = "queue_info_name";

    private final static String QUEUE_DEBUG_NAME = "queue_debug_name";

    private final static String QUEUE_ERROR_NAME = "queue_error_name";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_ROUTE_NAME);
    }

    @Bean
    public Queue infoQueue() {
        return new Queue(QUEUE_INFO_NAME, false, false, false, null);
    }

    @Bean
    public Queue debugQueue() {
        return new Queue(QUEUE_DEBUG_NAME, false, false, false, null);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(QUEUE_ERROR_NAME, false, false, false, null);
    }

    @Bean
    public Binding infoBindingExchange(Queue infoQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(infoQueue).to(directExchange).with("info");
    }

    @Bean
    public Binding debugBindingExchange(Queue debugQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(debugQueue).to(directExchange).with("debug");
    }

    @Bean
    public Binding errorBindingExchange(Queue errorQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
    }

    @Bean
    public Binding errorBind(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("debug");
    }
}
