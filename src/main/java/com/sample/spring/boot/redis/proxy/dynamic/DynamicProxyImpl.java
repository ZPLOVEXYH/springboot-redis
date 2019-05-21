package com.sample.spring.boot.redis.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理的方式实现aop
 */
public class DynamicProxyImpl implements InvocationHandler {

    private Object targetObject;

    public Object createProxy(Object targetObject) {
        this.targetObject = targetObject;

        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj;
        beforeLog();
        obj = method.invoke(targetObject, args);
        afterLog();

        return obj;
    }

    private void beforeLog() {
        System.out.println("开始执行");
    }

    private void afterLog() {
        System.out.println("执行完毕");
    }
}
