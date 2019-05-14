package com.sample.spring.boot.redis.inter;

import com.sample.spring.boot.redis.controller.LockController;
import com.sample.spring.boot.redis.domain.Demo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
public class TestAnnotation {

    /**
     * 读取注解的方法
     */
    public static void main(String[] args) {
        // 获取类上面的注解
        Class clazz = LockController.class;
        CacheLock cacheLock = (CacheLock) clazz.getAnnotation(CacheLock.class);
        System.out.println(cacheLock.toString());


        // 获取方法上面的注解
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            CacheLock cl = method.getAnnotation(CacheLock.class);

            log.info("cl : {} - {}", method.getName(), cl);

        }

        // 获取字段修饰的注解
        Class clazz2 = Demo.class;
        Field[] fields = clazz2.getFields();
        for (Field field : fields) {
            CacheParam cacheParam = field.getAnnotation(CacheParam.class);

            // 只能获取到public修改的字段
            log.info("cacheparam, {}", cacheParam.name());
        }

        try {
            Method method = clazz.getMethod("query", String.class);
            Parameter[] parameters = method.getParameters();
            for (Parameter param : parameters) {
                CacheParam cacheParam = param.getAnnotation(CacheParam.class);

                log.info("参数的注解：{}", cacheParam);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
