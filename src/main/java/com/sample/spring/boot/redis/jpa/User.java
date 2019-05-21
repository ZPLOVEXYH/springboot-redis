package com.sample.spring.boot.redis.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Table(value = "user")
public class User {

    private String name;
    private Integer age;
}
