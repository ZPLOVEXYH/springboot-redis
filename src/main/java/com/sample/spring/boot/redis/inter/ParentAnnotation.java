package com.sample.spring.boot.redis.inter;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited // 可以继承
public @interface ParentAnnotation {
}
