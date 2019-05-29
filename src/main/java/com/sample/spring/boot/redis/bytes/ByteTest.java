package com.sample.spring.boot.redis.bytes;

public class ByteTest {

    public static void main(String[] args) {
        String str = "A5";
        byte res = (byte) Integer.parseInt(str, 16);
        System.out.println(res);

        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            System.out.println(b);
        }

        String restr = String.format("%02x", new Integer(res & 0xff)).toUpperCase();
        System.out.println(restr);
    }
}
