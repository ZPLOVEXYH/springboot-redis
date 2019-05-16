package com.sample.spring.boot.redis.inter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestJpa {
    public static void main(String[] args) {
        new B();
    }
}

class A<String> {
    public A(){
        Class clazz = this.getClass();
        System.out.println(clazz.getName());

        // 得到超类
        Type type = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();

        Class actualTypeArgument = (Class) types[0];
        System.out.println(actualTypeArgument);
        System.out.println(clazz.getName());
    }
}

class B extends A<String> {

}
