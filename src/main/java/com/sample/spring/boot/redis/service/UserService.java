package com.sample.spring.boot.redis.service;

import com.sample.spring.boot.redis.domain.User;

public interface UserService {
    User findUserByAccountAndPassword(String account, String password);

    User findUserByUserId(long userId);
}
