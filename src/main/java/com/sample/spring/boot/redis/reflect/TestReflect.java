package com.sample.spring.boot.redis.reflect;

import com.sample.spring.boot.redis.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;

@Slf4j
public class TestReflect {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = User.class;
        // 通过java反射机制获取得到方法
        Method[] methods = clazz.getDeclaredMethods();
        Object obj = clazz.newInstance();
        for (Method method : methods) {
            log.info("方法名称列表：{}", method.getName());

            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters){
                log.info("方法的参数名称，{}",parameter);
            }
            if(method.getName().equals("setAccount")){
                method.invoke(obj, "1111");
            }
        }

        log.info("User.tostring, {}", ((User) obj).toString());


        // 获取的得到字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            log.info("方法的修饰符- {}", Modifier.toString(field.getModifiers()));
            log.info("字段名称列表：{}", field.getName());
        }

        log.info("类的修饰符- {}", Modifier.toString(clazz.getModifiers()));

        Constructor[] constructors = clazz.getConstructors();
        for(Constructor constructor : constructors) {
            log.info("构造函数名称，{}", constructor.getName());
        }

    }
}
