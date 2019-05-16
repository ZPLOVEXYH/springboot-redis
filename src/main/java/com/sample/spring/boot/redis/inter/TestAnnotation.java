package com.sample.spring.boot.redis.inter;

import com.sample.spring.boot.redis.bean.Parent;
import com.sample.spring.boot.redis.bean.Sub;
import com.sample.spring.boot.redis.controller.LockController;
import com.sample.spring.boot.redis.domain.Demo;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
public class TestAnnotation {

    /**
     * 读取注解的方法
     */
    public static void main(String[] args) throws NoSuchMethodException {
        // 获取类上面的注解
        Class clazz = LockController.class;
        CacheLock cacheLock = (CacheLock) clazz.getAnnotation(CacheLock.class);
        System.out.println(cacheLock.toString());


        // 获取方法上面的注解
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if("query".equals(methodName) || "demo".equals(methodName)) {
                log.info("cl : {} - {}", method.getName(), clazz.getAnnotation(CacheLock.class));
                CacheLock cl = method.getAnnotation(CacheLock.class);
            }

        }

        // 获取字段修饰的注解
        Class clazz2 = Demo.class;
        // 注解的获取跟属性和方法是否私有没有关系，根据Class.getDeclaredMethods() 和 Class.getDeclaredFields()可以获取私有方法和私有属性
        Field[] fields = clazz2.getDeclaredFields();
        for (Field field : fields) {
            //
            boolean bool = field.isAnnotationPresent(CacheParam.class);
            log.info("bool - {}", bool);

            CacheParam cacheParam = field.getAnnotation(CacheParam.class);

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

//        Annotation[] annotations = Sub.class.getAnnotations();
//        printAnnotation("all", annotations);
//
//        Annotation[] annotations2 = Sub.class.getDeclaredAnnotations();
//        printAnnotation("parent", annotations2);
//
//        Annotation annotation = Sub.class.getAnnotation(SubAnnotation.class);
//        log.info("sub - {}", annotation);
//
//        Annotation parentAnnotation = Sub.class.getAnnotation(ParentAnnotation.class);
//        log.info("parent - {}", parentAnnotation);
//
//        // 获取方法上面的注解信息
//        Method[] methods = Sub.class.getMethods();
//        for (Method method : methods) {
//            if(method.getName().equals("test")) {
//                Annotation[] anns = method.getAnnotations();
//                printAnnotation("method annotation", anns);
//
//                Annotation[] anns1 = method.getDeclaredAnnotations();
//                printAnnotation("method anns1", anns1);
//
//                Annotation ann1 = method.getAnnotation(SubAnnotation.class);
//                printAnnotation("sub method", ann1);
//
//                Annotation ann2 = method.getAnnotation(ParentAnnotation.class);
//                printAnnotation("parent method", ann2);
//            }
//        }


    }

    /**
     * 解析注解内容
     * @param msg
     * @param annotations
     */
    public static void printAnnotation(String msg, Annotation... annotations) {
        log.info("[---------------------- msg - {} ------------------------------]", msg);

        if(annotations.length == 0 || annotations == null) {
            log.info("annotation is null");
        }
        for (Annotation annotation : annotations) {
            log.info("annotation - {}", annotation);
        }
    }
}
