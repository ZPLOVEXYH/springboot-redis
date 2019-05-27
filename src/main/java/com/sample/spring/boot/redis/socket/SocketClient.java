package com.sample.spring.boot.redis.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        try {
            //使用Socket，建立与服务端的连接
            Socket client = new Socket("127.0.0.1", 4444);
            InputStream in = client.getInputStream();//获得输入流
            OutputStream out = client.getOutputStream();//获得输出流
            // 包装输入流，输出流
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));
            PrintWriter outWriter = new PrintWriter(out);
            // 获得控制台输入
            BufferedReader inConsole = new BufferedReader(new InputStreamReader(in));
            while (true) {
                String str = inConsole.readLine();//读取控制台输入
                outWriter.println(str);//输出到服务端
                outWriter.flush();//刷新缓冲区
                if (str.equals("end")) {
                    break;
                }//退出
                //读取服务端输出
                System.out.println(inRead.readLine());
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
