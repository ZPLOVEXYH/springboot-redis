package com.sample.spring.boot.redis;

import com.sample.spring.boot.redis.interceptor.LockMethodInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.sample.spring.boot.redis.dao")
@EnableCaching
// session过期时间30秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
//@ImportAutoConfiguration(LockMethodInterceptor.class)
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }


    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        // 让springSession不再执行config命令
        return ConfigureRedisAction.NO_OP;
    }

}
