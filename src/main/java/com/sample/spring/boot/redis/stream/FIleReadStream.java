package com.sample.spring.boot.redis.stream;

import java.io.*;

public class FIleReadStream {

    public static void main(String[] args) {
        File file = new File("d:\\test.txt");
        try {
            InputStream inputStream = new FileInputStream(file);
            System.out.println(file.length());
            System.out.println(inputStream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(!file.exists()) {
//            new RuntimeException("目标文件不存在");
//        } else {
//            InputStream inputStream = null;
//            try {
//                inputStream = new FileInputStream(file);
//                ObjectInputStream ois = new ObjectInputStream(inputStream);
//                TestBean testBean = (TestBean) ois.readObject();
//                System.out.println(testBean.toString());
//
//                TestBean testMap2 = (TestBean) ois.readObject();
//                System.out.println(testMap2.toString());
//
//                inputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//
//            }
//        }
    }
}
