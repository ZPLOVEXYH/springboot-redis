package com.sample.spring.boot.redis.proxy;

import com.sample.spring.boot.redis.jpa.User;

import java.util.List;

public interface IUserServ {
    List<User> findAllUser();

    int deleteUserById(User user);

    int saveUser(User user);
}
