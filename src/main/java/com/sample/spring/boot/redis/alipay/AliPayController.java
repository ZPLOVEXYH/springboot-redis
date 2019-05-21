package com.sample.spring.boot.redis.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliPayController {

    @Autowired
    OrderPayService orderPayService;

    @RequestMapping("alipay")
    public String testAlipay() {
        return orderPayService.aliPayTradePay("江苏版权-确权保护", "20190517025116981350774", 0.01);
    }

}
