package com.sample.spring.boot.redis.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class MyException extends Exception implements Serializable {

    private Integer code;
    private String mesage;

    public MyException(String message) {
        super(message);
    }

    public MyException(int code, String mesage) {
        this.code = code;
        this.mesage = mesage;
    }
}
