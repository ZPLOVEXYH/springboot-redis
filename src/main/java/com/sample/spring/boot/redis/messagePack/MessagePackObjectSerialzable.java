package com.sample.spring.boot.redis.messagePack;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MessagePackObjectSerialzable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1, "zhangsan", "nanjing");
        Student student1 = new Student(2, "lisi", "hangzhou");
        Student student2 = new Student(3, "wangwu", "chongqing");

        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);

        Student stu = new Student(4, "other", "suzhou", studentList);

//        try {
//            byte[] bytes = MessagePack.pack(stu);
//            log.info("bytes{}", bytes);
//
//            Student sdt = MessagePack.unpack(bytes, Student.class);
//            log.info("打印出来的对象字符串为：{}", sdt);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        FileOutputStream fileOutputStream = new FileOutputStream("d:/test.txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(stu);
//
//        objectOutputStream.close();
//        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("d:/test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Student s = (Student) objectInputStream.readObject();
        log.info("对象信息为：{}", s);

        objectInputStream.close();
        fileInputStream.close();
    }
}
