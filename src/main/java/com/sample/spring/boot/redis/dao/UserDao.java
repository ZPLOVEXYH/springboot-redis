package com.sample.spring.boot.redis.dao;

import com.sample.spring.boot.redis.domain.User;
import org.springframework.data.repository.query.Param;

public interface UserDao {

    User findUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    User findUserByUserId(@Param("userId") long userId);
}
