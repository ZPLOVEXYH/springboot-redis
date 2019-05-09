package com.sample.spring.boot.redis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {

    private Long userId;
    private String account;
    private String password;
}
