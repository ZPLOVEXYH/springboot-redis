package com.sample.spring.boot.redis.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringReadChannel {

    public static void main(String[] args) {
        try {
            File file = new File("d://test.txt");
            FileChannel fc = new FileInputStream(file).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(40);
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(14);
            ByteBuffer[] byteBuffers = {byteBuffer2, byteBuffer};
            int i = 0;
            while (fc.read(byteBuffers) != -1) {
                i++;
                byteBuffer.flip();
                byteBuffer.clear();
                System.out.println("byteBuffer：" + byteBuffer.position());
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                System.out.println();
                System.out.println("byteBuffer2：" + byteBuffer2.position());
                byteBuffer2.flip();
                System.out.println("此缓存区剩余的元素数：" + byteBuffer2.remaining());
                while (byteBuffer2.hasRemaining()) {
                    System.out.print((char) byteBuffer2.get());
                }
                System.out.println();
                byteBuffer2.clear();
            }
            System.out.println("循环执行的次数为：" + i);
            fc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
