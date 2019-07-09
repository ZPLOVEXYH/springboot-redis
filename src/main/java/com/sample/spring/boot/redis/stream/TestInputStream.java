package com.sample.spring.boot.redis.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestInputStream {

    public static void main(String[] args) throws IOException {
//        File file = new File("d://test.txt");
//        try {
//            InputStream inputStream = new FileInputStream(file);
//            System.out.println("文件的总字节长度：" + inputStream.available());
//            int i = 0;
//            byte[] bytes = new byte[10];
//            inputStream.mark(50);
//            inputStream.reset();
//            while ((i = inputStream.read(bytes)) != -1) {
//                System.out.println(new String(bytes, 0, i));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String content = "hello world!!!";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        int b = 0;
        boolean marked = false;
        while ((b = inputStream.read()) != -1) {
            char c = (char) b;
            System.out.print(c);

            if ((c == ' ') && !marked) {
                inputStream.mark(content.length());
                marked = true;
            }

            if ((c == 'd') && marked) {
                inputStream.reset();
                marked = false;
            }
        }
    }
}
