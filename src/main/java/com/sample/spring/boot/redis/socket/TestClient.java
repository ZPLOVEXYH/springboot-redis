package com.sample.spring.boot.redis.socket;

import java.io.IOException;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3333);
            socket.getOutputStream().write("hello world".getBytes());

            System.out.println("客户端准备向服务端发送消息内容");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
