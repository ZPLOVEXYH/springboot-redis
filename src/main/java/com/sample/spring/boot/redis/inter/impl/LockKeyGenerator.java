package com.sample.spring.boot.redis.inter.impl;

import com.sample.spring.boot.redis.inter.CacheKeyGenerator;
import com.sample.spring.boot.redis.inter.CacheLock;
import com.sample.spring.boot.redis.inter.CacheParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Service
public class LockKeyGenerator implements CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     * @param joinPoint
     * @return
     */
    @Override
    public String getLockKey(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        final Object[] objects = joinPoint.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuffer stringBuffer = new StringBuffer();
        // 默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
        for(int i = 0; i < parameters.length; i++) {
            final CacheParam cacheParam = parameters[i].getAnnotation(CacheParam.class);
            if(null == cacheParam){
                continue;
            }

            stringBuffer.append(cacheLock.delimiter()).append(objects[i]);
        }

        if(StringUtils.isEmpty(stringBuffer)) {
            final Annotation[][] annotations = method.getParameterAnnotations();
            for(int j = 0; j < annotations.length; j++){
                final Object obj = objects[j];
                Field[] fields = obj.getClass().getDeclaredFields();
                for(int t = 0; t < fields.length; t++){
                    final CacheParam cp = fields[t].getAnnotation(CacheParam.class);
                    if(null == cp){
                        continue;
                    }

                    stringBuffer.append(cacheLock.delimiter()).append(ReflectionUtils.getField(fields[t], obj));
                }
            }
        }

        // 返回生成好的redis分布式锁的key
        return cacheLock.prefix() + stringBuffer.toString();
    }
}
