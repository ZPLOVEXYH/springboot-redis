package com.sample.spring.boot.redis.jpa;

public class UserDao extends DemoDao<User> {

    @Override
    public void exec(User user) {
        super.exec(user);
    }

}
