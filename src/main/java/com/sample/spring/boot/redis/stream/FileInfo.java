package com.sample.spring.boot.redis.stream;

import java.io.File;

public class FileInfo {

    public static void main(String[] args) {
        File file = new File("d://test.txt");
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("文件最后一次的修改时间：" + file.lastModified());
        System.out.println("文件的大小：" + file.length());
        System.out.println("文件是否可读：" + file.canRead());
        System.out.println("文件是否可写：" + file.canWrite());
        System.out.println("文件是否可执行：" + file.canExecute());
        System.out.println("文件的相对路径：" + file.getPath());
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("判断是否是文件：" + file.isFile());
        System.out.println("判断是否是文件夹：" + file.isDirectory());
        System.out.println("获取得到文件名称：" + file.getName());
        System.out.println("获取得到文件所述的父目录：" + file.getParent());

    }
}
