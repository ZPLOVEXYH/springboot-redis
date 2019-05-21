package com.sample.spring.boot.redis.alipay;

public interface OrderPayService {

    String aliPayTradePay(String name, String orderNo, Double price);
}
