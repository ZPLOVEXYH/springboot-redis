package com.sample.spring.boot.redis.alipay;

public class AlipayConfig {

    // 支付宝网关（固定）
    // "https://openapi.alipaydev.com/gateway.do";//沙箱网关
    public static final String GATEWAYURL = "https://openapi.alipay.com/gateway.do";
//    public static final String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // APPID 即创建应用后生成
    public static final String APPID = "xx";// 正式
//     public static final String APPID = "2016091400508498";// 沙箱

    // 开发者私钥，由开发者自己生成
    public static final String APP_PRIVATE_KEY = "xxx";
//    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtXKWs+trRSuCxEUvlsEeSAuLWs3B/Dh74R8223BnfzoA29aGeoycAqfKlBMcbzU2G1KayESvZKGpwBLeejSbecRYjgZsQDyEaDimQQJtGFvZVV6u4XNnvIJ72eQzEaEIQfuiorjBTLm6DQuds4R0GxftqON6QFoIZkWB9ZrZKd02cuy16dW+UqtLVGGAHcCIAkB63zUiKSNfweMddneZ7MVs3lvu3xhMnD+5us/+n2Vp4qhfmpYLcdqIW6InU4GypeoOpyUTrfUGpgdR0l924vHy/GQJZEKFaRcK3cYK+ECyKpSIoqaJJFLHbkqsliuPpMUG+rM3jiqeIAH4psAznAgMBAAECggEBAJ5jyEbbxrJzrAh7GhHX1fwUQPYSadTbrPYAfHX2cHlnrQMJtsk+nTLhEv0r+VJwZ8WpYkfMong8kcqYtL7ajcmsHqMAFhE9EWxBxj2ymWsXLabZe93sj30IG9Rq0nxcGQgDO0RqKWLGSFgK93Al2KRInKT3InkY53K+vR61ihVLmGf7+GwPtIZteBy+tuAyvcj2RlkYvjiFi3ySyZ1wA3sJIlgrGiixd6fj20XFGNE3CnAwu0BJgXXbP/S9J4C0RRa3ZXj8fX7oONhVxz2xKxn7AT4e8OWjt7J41H2LRct8Fgl9pqgz2FJYv/WfbkG8x9fGiKYYvPXoxjjrk/tkewkCgYEA8f9Lcu5JPrE9rpw9zlwhm5cOO81xLxdwL5R5/1bRP48BZGIYuqlCbVvjJVqtO8eTnLhUwH7fG8B7cmoeO9bGr9GQrtfyCqz6FtVymTBieJlfgZDVhtzyv2qKOBMIFE8jsbSBK/NHHMvykJ+XdQ1riwCeQDdXICRuYTTFwGk2OsUCgYEAt2SoN95tVmVrvKG6ATLNEtge/ozeVywA4GjltrSw/G9vqp+DkkT2pY19uROuzMazoTzKWpPho2q/qzNlv/ANbOFM2GEmKamQ7CO88JgRxMsPTvc/HxCLU/ClMJU8LcOf9LfP2KYZpPwuheKJoF4vDGj8NsbFmccJyYSdpkNEk7sCgYBJlL2FMaz1sgC2Ue19DIhvfaunRV1P20mSPgwmNmijccETm7w3LXX0OIdFeV/JGHLqqSWj7i+6iXk/ncKZoUGCfi8G6sQ+uL/GJ5qTt6GJV+ExTS+PtSjeSO/EAw1m13Vb+C16hpstx1l23f+4aJ81gbecgPct38XsKpaiXZtOnQKBgQCMsN7QRYYxwoq9YoDUzIlAzKYyeBVWYL6najHYUZR5hG/xQIBqZRem9/4cTvpJxKInrwA6LrrqaEl0aHDFp75U6h7O3PCvA5PXZK9dD/yJsZIj7U/yX/nTQokn1UUegrYiwiTkusBvrrtuINWePsLvTVc4GpObHnPmsiNTWsWwYwKBgENaeTNOCHV2km/ysXQSEIhKbtlAMQPsgWHCt/bzHlF9m18izb1LrJyjzcSsd+Zy78R+pv4G50Q27c3e/DFPz/wYxN/yHWRbyLBA8ipJbCtMtPEdS9krpmN6cChIdLGbz4CVUqOPSRzNb9lhhgPCcCNRq6DG3HBceb1Se9VnO3zk";

    // 参数返回格式，只支持json
    public static final String FORMAT = "json";

    // 参数返回格式，只支持json
    public static final String CHARSET = "UTF-8";

    // 支付宝公钥，由支付宝生成
    public static final String ALIPAY_PUBLIC_KEY = "ddd";
//    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApFQKccMq+wPoWh93DcX3XYrnT7FJ3gntJA/jEwgk6Jd3iEVS2CyUCCgFVcQn8xjXT81YbZHYvoC50IBuu+A+Ey+J8VIgsBm5g9uwbOLRf66GrZjuKOlalHm5gHXjcL2gZRMBJEStOxstcO2YQriqhQzdL3EKp+HQc9u14IOVFpZdR8qq1l7CzKHn1vQo/1fUPfUrTLQqSuQTU9Ospv/QHFqOJA7DPetUQ+jnaZ082f3clr4ITw4EE8A6IWqTXcETTx5j/udCGP84g2Y4j+8i9DqYGyD5ePVgt4G0ICBX1bi1qNlylfxRg8Y3c1DFrRGyr0NpKQxSVXkYaVNvrCoudQIDAQAB";

    // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
    public static final String SIGN_TYPE = "RSA2";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外	网可以正常访问
    public static final String NOTIFY_URL = "http://公网地址";

    // 同步返回地址，HTTP/HTTPS开头字符串，非必填
    public static final String RETURN_URL = "http://公网地址";

    public static final String TimeoutExpress = "1m";// 交易的过期时间，要与redis的二维码同步的
}