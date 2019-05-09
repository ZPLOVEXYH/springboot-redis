package com.sample.spring.boot.redis.service.impl;

import com.sample.spring.boot.redis.dao.UserDao;
import com.sample.spring.boot.redis.domain.User;
import com.sample.spring.boot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findUserByAccountAndPassword(String account, String password) {
        return userDao.findUserByAccountAndPassword(account, password);
    }

    @Override
    public User findUserByUserId(long userId) {
        return userDao.findUserByUserId(userId);
    }
}
