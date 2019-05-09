package com.sample.spring.boot.redis.bean;

import lombok.Getter;

@Getter
public enum StatusCode {

    NEED_LOGIN(1),
    USER_NOT_EXIST(2),
    REQUEST_SUCCESS(3),
    ACCOUNT_OR_PASSWORD_ERROR(4);

    private Integer value;

    StatusCode(Integer value) {
        this.value = value;
    }
}
