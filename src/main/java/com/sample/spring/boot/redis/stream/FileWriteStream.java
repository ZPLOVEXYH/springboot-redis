package com.sample.spring.boot.redis.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class FileWriteStream {

    public static void main(String[] args) {
//        TestBean testBean = new TestBean();
//        testBean.setId(1L);
//        testBean.setUsername("zhangsan");
//        testBean.setPassword("111111");
//
//        TestBean testBean2 = new TestBean();
//        testBean.setId(2L);
//        testBean.setUsername("lisi");
//        testBean.setPassword("222222");
//
//        Map<String, Object> testHash = new HashMap<>();
//        testHash.put("testKey", "testValue");
//
//        File file = new File("D:\\test.txt");
//        try {
//            OutputStream outputStream = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//            oos.writeObject(testBean);
//            oos.writeObject(testBean2);
//
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        String message = "helloworld";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b = 0;
        while ((b = byteArrayInputStream.read()) != -1) {
            byteArrayOutputStream.write(b);
        }

    }
}
