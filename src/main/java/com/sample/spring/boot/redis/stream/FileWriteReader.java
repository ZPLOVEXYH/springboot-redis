package com.sample.spring.boot.redis.stream;

import java.io.*;
import java.util.*;

public class FileWriteReader {

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("d://test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Map<String, String> fileMap = new HashMap<>();
            List<String> stringList = new ArrayList<>();
            // 读取文件一行的数据
            String str = "";
            // 截取获得数据的分数
            String score = "";
            while ((str = bufferedReader.readLine()) != null) {
                score = str.substring(str.length() - 2);

                stringList.add(score);
                fileMap.put(score, str);
            }

            // 将集合倒序排
            Collections.sort(stringList);
            Collections.reverse(stringList);
            List<String> valueList = new ArrayList<>();
            stringList.stream().forEach(x ->
                    valueList.add(fileMap.get(x))
            );

            valueList.stream().forEach(System.out::println);

            File file = new File("d://test2.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            valueList.stream().forEach(x -> {
                try {
                    bufferedWriter.write(x);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
