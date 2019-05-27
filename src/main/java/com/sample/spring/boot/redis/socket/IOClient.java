package com.sample.spring.boot.redis.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author 闪电侠
 * @date 2018年10月14日
 * @Description:客户端
 */
public class IOClient {

    public static void main(String[] args) {
        // 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {
            try {
                // 通过IP地址和端口实例化Socket，请求连接服务器
                Socket socket = new Socket("192.168.176.134", 3333);
                for (int i = 0; i < 100; i++) {
                    try {
                        // 获得Socket上的流以进行读写
                        socket.getOutputStream().write((new Date() + ": hello world" + i).getBytes());

                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
//                while (true) {
//                    try {
//                        // 获得Socket上的流以进行读写
//                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
//
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                    }
//                }
            } catch (IOException e) {
            }
        }).start();

    }

}
