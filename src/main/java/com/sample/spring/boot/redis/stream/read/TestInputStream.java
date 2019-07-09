package com.sample.spring.boot.redis.stream.read;

import java.io.*;

public class TestInputStream {

    public static void main(String[] args) {
        try {
            File file = new File("d:/test.txt");
            InputStreamReader inputStreamReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str1 = bufferedReader.readLine();
            System.out.println(str1);
            String str2 = bufferedReader.readLine();
            System.out.println(str2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
