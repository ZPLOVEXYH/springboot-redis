package com.sample.spring.boot.redis.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CgLibTest implements MethodInterceptor {

    private Object object;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法监听开始");
//        Object obj = method.invoke(object, objects);
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("方法监听结束");

        return obj;
    }

    public Object testCgLib(Object obj) {
        this.object = obj;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new CgLibTest());

        return enhancer.create();
    }

}
