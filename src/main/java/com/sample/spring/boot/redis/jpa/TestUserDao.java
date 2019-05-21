package com.sample.spring.boot.redis.jpa;

public class TestUserDao {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user = new User("zhangsan", 10);
        userDao.exec(user);
    }
}
