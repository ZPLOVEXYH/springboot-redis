package com.sample.spring.boot.redis.alipay;

import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.stereotype.Service;

@Service
public class AliPayServiceImpl implements OrderPayService {

    static AlipayManger instance = AlipayManger.getInstance();//获取我们封装的 支付实例

    /**
     * ali电脑支付，前端调用这个接口，将body展示到页面上，就可以跳到支付宝三方到页面上了
     */
    @Override
    public String aliPayTradePay(String name, String orderNo, Double price) {
        AlipayTradePagePayResponse response = instance.TradePagePay(orderNo, price, name);
        System.out.println(response.getBody());
        // 是form表单，我们最终需要的二维码数据
        return response.getBody();
    }
}
