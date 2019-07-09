package com.sample.spring.boot.redis.stream.write;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestOutPutStream {
    public static void main(String[] args) {
        List<String> strArray = new ArrayList<>();
        strArray.add("Name: Anna");
        strArray.add("Age: 25");
        strArray.add("Email: anna@mailserver.com");
        strArray.add("Phone: 1234567890");

        File file = new File("d:/test");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            for (String x : strArray) {
                try {
                    outputStream.write(x.getBytes());
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
