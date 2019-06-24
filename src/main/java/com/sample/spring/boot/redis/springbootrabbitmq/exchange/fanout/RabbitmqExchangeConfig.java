//package com.sample.spring.boot.redis.springbootrabbitmq.exchange.fanout;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitmqExchangeConfig {
//
//    private final static String EXCHANGE_FANOUT_NAME = "exchange_fanout";
//
//    private final static String QUEUE_SMS_NAME = "queue_sms_name";
//
//    private final static String QUEUE_EMAIL_NAME = "queue_email_name";
//
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(EXCHANGE_FANOUT_NAME);
//    }
//
//    @Bean
//    public Queue smsQueue() {
//        return new Queue(QUEUE_SMS_NAME, false, false, false, null);
//    }
//
//    @Bean
//    public Queue emailQueue() {
//        return new Queue(QUEUE_EMAIL_NAME, false, false, false, null);
//    }
//
//    @Bean
//    public Binding smsQueueBindingExchange(Queue smsQueue, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
//    }
//
//    @Bean
//    public Binding emailQueueBindingExchange(Queue emailQueue, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
//    }
//}
