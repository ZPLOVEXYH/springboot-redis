package com.sample.spring.boot.redis.controller;

import com.sample.spring.boot.redis.domain.Demo;
import com.sample.spring.boot.redis.inter.CacheLock;
import com.sample.spring.boot.redis.inter.CacheParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class LockController {

    @CacheLock(prefix = "test", expire = 60)
    @GetMapping("/test")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @CacheLock(prefix = "demo", expire = 30)
    @PostMapping("/demo")
    public String demo(@RequestBody Demo demo) {
        return "success - " + demo.toString();
    }
}
