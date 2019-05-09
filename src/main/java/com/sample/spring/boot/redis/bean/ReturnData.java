package com.sample.spring.boot.redis.bean;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ReturnData<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ReturnData(int code, T data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
