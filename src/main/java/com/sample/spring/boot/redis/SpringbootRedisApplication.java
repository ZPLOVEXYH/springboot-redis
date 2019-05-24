package com.sample.spring.boot.redis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.sample.spring.boot.redis.dao")
@EnableCaching
// session过期时间30秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
@ImportResource(locations = "classpath:spring-bean.xml")
//@ImportAutoConfiguration(LockMethodInterceptor.class)
@Slf4j
public class SpringbootRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootRedisApplication.class, args);
        String[] strings = applicationContext.getBeanDefinitionNames();
        for (String str : strings) {
            log.info("定义的bean：{}", str);
        }

//        new SpringApplicationBuilder().sources(SpringbootRedisApplication.class).run(args);
    }


    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        // 让springSession不再执行config命令
        return ConfigureRedisAction.NO_OP;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("执行SpringbootRedisApplication的run方法。。。");
    }
}
