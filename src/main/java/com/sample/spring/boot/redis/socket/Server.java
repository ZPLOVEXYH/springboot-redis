package com.sample.spring.boot.redis.socket;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Setter
@Getter
public class Server extends Thread {

    //服务器端口号
    private int port = 3333;

    private ServerSocket server = null;

    public Server() throws IOException {
        //创建一个服务器，同时可以接收10个设备发送的数据
        server = new ServerSocket(port, 10);
        System.out.println("Socket服务器启动，开始接受数据");
    }

    @Override
    public void run() {
        while (true) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            byte[] inputData = new byte[1024];
            try {
                //接收Socket数据
                socket = server.accept();
                //获取远程采集机的IP和端口号
                String remoteAddress = socket.getInetAddress().toString();
                int remotePort = socket.getPort();
                inputStream = socket.getInputStream();
                //获取传入的数据长度
                int length = inputStream.read(inputData);
                //创建输出流向客户端返回信息
                outputStream = socket.getOutputStream();
                if (length == 10) {
                    //如果长度等于10，说明传入的是心跳包
                    System.out.println("接收到心跳包，客户端信息[" + remoteAddress + ":" + remotePort + "]");
                    outputStream.write("success".getBytes());
                } else {
                    System.out.println("接收的信息有误.");
                    outputStream.write("failed".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
