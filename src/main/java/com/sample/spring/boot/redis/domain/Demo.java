package com.sample.spring.boot.redis.domain;

import com.sample.spring.boot.redis.inter.CacheParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Demo {

    @CacheParam(name = "token")
    private String token;

    public void doTest() {
        System.out.println("test do...");
    }
}
