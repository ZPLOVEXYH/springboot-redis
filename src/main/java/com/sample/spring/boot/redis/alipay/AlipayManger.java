package com.sample.spring.boot.redis.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author
 * @Description:电脑网站支付
 */
public class AlipayManger {

    /**
     * alipayClient只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象。
     */
    private static volatile AlipayManger instance;
    private static volatile AlipayClient alipayClient;

    private AlipayManger() {
        alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAYURL, AlipayConfig.APPID,
                AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);
    }

    public static AlipayManger getInstance() {
        if (instance == null) {
            synchronized (AlipayManger.class) {
                if (instance == null) {
                    instance = new AlipayManger();
                }
            }
        }
        return instance;
    }

    /**
     * 支付页面：支付操作
     *
     * @param out_trade_no 订单号
     * @param total_amount 支付总金额
     * @param subject      商品名称
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradePagePayResponse TradePagePay(String out_trade_no, Double total_amount, String subject) {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();//用来封装参数的一个类
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(String.valueOf(total_amount));
        model.setSubject(subject);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setTimeoutExpress(AlipayConfig.TimeoutExpress);
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);//设置-异步通知页面
        request.setReturnUrl(AlipayConfig.RETURN_URL);//设置-支付成功后同步跳转到哪个页面
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 统一订单查询接口
     *
     * @param out_trade_no 订单号
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse TradePayQuery(String out_trade_no) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(out_trade_no);
        request.setBizModel(model);
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 基本退款接口
     *
     * @param out_trade_no 订单号
     * @param refundAmount 退款金额
     * @param refundReason 退款理由
     * @return
     */
    public AlipayTradeRefundResponse TradeRefund(String out_trade_no, String refundAmount, String refundReason) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(out_trade_no);
        model.setRefundAmount(refundAmount);
        model.setRefundReason(refundReason);
        request.setBizModel(model);
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 商户的退款查询接口
     *
     * @param out_trade_no   订单号
     * @param out_request_no 部分退款传参
     * @return
     */
    public AlipayTradeFastpayRefundQueryResponse TradeFastpayRefundQuery(String out_trade_no, String out_request_no) {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(out_trade_no);
        boolean flag = StringUtils.isEmpty(out_request_no);
        model.setOutRequestNo(flag ? out_trade_no : out_request_no);
        request.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;

    }

    /**
     * 支付宝服务器异步通知
     * 此处要当成一个接口可以通过url进行访问，并且只有放到公网上，
     * 在支付里面配置了request.setNotifyUrl(AlipayConfig.NOTIFY_URL
     * 程序支付成功后才会异步处理，失败和订单超时不会进行异步的
     *
     * @param request
     * @throws UnsupportedEncodingException
     */
    public void NotifyUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
                    AlipayConfig.SIGN_TYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } // 调用SDK验证签名
        if (signVerified) {// 验证成功，进行业务逻辑的处理
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            if (trade_status.equals("TRADE_SUCCESS")) {
                /**
                 * 返回的状态是success，接下来可以：更改订单支付成功状态，设置支付信息，等等业务处理……
                 */
                System.out.println("success");
            } else {
                /**
                 *  fail 的业务处理：……
                 */
                System.out.println("fail");
            }
        }
    }

    /**
     *  对于支付宝同步跳转页面（return_url），里面的代码和异步的是一样的
     *  我们可以在同步页面中拿到支付宝给你返回的订单信息，然后写个接口，将数据返给前端，让前端进行展示
     */
}
