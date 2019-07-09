package com.sample.spring.boot.redis.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

    public static void main(String[] args) {
//        try {
//            // 获取通道，该通道允许写操作
//            FileChannel fc = new FileOutputStream("d:/test.txt").getChannel();
//            // 往文件通道中写入some text文本内容
//            fc.write(ByteBuffer.wrap("Some text".getBytes()));
//            // 关闭文件通道
//            fc.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            File file = new File("d://test.txt");
//            // 随机读取文件流创建的管道
//            FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
//            // fc.position()计算从文件的开始到当前位置之间的字节数
//            System.out.println("此通道的文件位置：" + fc.position());
//            System.out.println("此通道的文件大小：" + fc.size());
//            // 设置此通道的文件位置,fc.size()此通道的文件的当前大小,该条语句执行后，通道位置处于文件的末尾
//            fc.position(fc.size() - 1);
//            System.out.println("此通道的文件位置：" + fc.position());
//            fc.write(ByteBuffer.wrap("Some more".getBytes()));
//
//            System.out.println("当前位置：" + fc.position(0));
//            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
//            fc.read(byteBuffer);
//            System.out.println("读取之前的当前位置：" + byteBuffer.position());
//            System.out.println("读取之前的文件大小：" + byteBuffer.limit());
//            System.out.println("读取之前的容量大小：" + byteBuffer.capacity());
//            byteBuffer.flip();
//            System.out.println("读取之后的当前位置：" + byteBuffer.position());
//            System.out.println("读取之后的文件大小：" + byteBuffer.limit());
//            System.out.println("读取之后的容量大小：" + byteBuffer.capacity());
//            while(byteBuffer.hasRemaining()){
//                System.out.print((char) byteBuffer.get());
//            }
//            byteBuffer.clear();
//            fc.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            // 用通道读取文件
//            FileChannel fc = new FileInputStream("d://test.txt").getChannel();
//            // 重新分配一个缓冲区
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            // 将通道中的文件内容读取到缓冲区中
//            fc.read(byteBuffer);
//            System.out.println("调换之前：" + byteBuffer.position());
//            System.out.println("limit之前：" + byteBuffer.limit());
//            System.out.println("capacity之前：" + byteBuffer.capacity());
//            byteBuffer.flip();
//            System.out.println("调换之后：" + byteBuffer.position());
//            System.out.println("limit之后：" + byteBuffer.limit());
//            System.out.println("capacity之后：" + byteBuffer.capacity());
//            while(byteBuffer.hasRemaining()){
//                System.out.print("test: " + (char) byteBuffer.get());
//            }
//            fc.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            File file = new File("d://test.txt");
            FileChannel fc = new FileInputStream(file).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(41);
            int byteRead = fc.read(byteBuffer);
            int i = 0;
            int j = 0;
            System.out.println("能够读取到的数据长度为：" + byteBuffer.limit());
            while (byteRead != -1) {
                i++;
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    j++;
                    System.out.println((char) byteBuffer.get());
                }

                byteBuffer.clear();
                byteRead = fc.read(byteBuffer);
            }
            System.out.println("循环的次数：" + i + "，里面循环的次数为：" + j);
            fc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
