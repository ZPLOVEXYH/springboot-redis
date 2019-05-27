package com.sample.spring.boot.redis.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) {
        try {
            // 建立ServerSocket对象，监听绑定端口
            ServerSocket server = new ServerSocket(4444);
            // 建立接收Socket，阻塞响应
            Socket client = server.accept();
            // 获得输入流，用于接收客户端信息
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            // 获得输出流，用于输出客户端信息
            // 对输入流进行包装，方便使用
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));
            // 对输出流进行包装，方便使用
            PrintWriter outWriter = new PrintWriter(out);
            while (true) {
                // 读入
                String str = inRead.readLine();
                outWriter.println("输出到客户端");
                // 输出
                outWriter.flush();
                if (str.equals("end")) {
                    break;
                }
            }

            // 关闭Socket
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
