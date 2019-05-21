package com.sample.spring.boot.redis.proxy;

import com.sample.spring.boot.redis.jpa.User;

import java.util.List;

public class IUserProxyImpl implements IUserServ {

    private IUserServ iUserServ;

    public IUserProxyImpl(IUserServ iUserServ) {
        this.iUserServ = iUserServ;
    }

    private void beforeLog() {
        System.out.println("开始执行");
    }

    private void afterLog() {
        System.out.println("执行完毕");
    }

    @Override
    public List<User> findAllUser() {
        beforeLog();
        iUserServ.findAllUser();
        afterLog();
        return null;
    }

    @Override
    public int deleteUserById(User user) {
        beforeLog();
        iUserServ.deleteUserById(user);
        afterLog();
        return 0;
    }

    @Override
    public int saveUser(User user) {
        beforeLog();
        iUserServ.saveUser(user);
        afterLog();
        return 0;
    }
}
