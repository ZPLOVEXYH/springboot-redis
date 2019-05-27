package com.sample.spring.boot.redis.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                String s = new String(bytes, 0, len);
                System.out.println("socket服务端接收到客户端的内容为：" + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
