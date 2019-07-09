package com.sample.spring.boot.redis;

import com.sample.spring.boot.redis.domain.City;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @Test
//    public void contextLoads() {
//        String message = "hello rabbitmq!!!";
//        amqpTemplate.convertAndSend("test_simple_queue", message);
//        System.out.println("发送消息" + message + "，ok");
//    }

//    @Test
//    public void testWorkQueue(){
//        for (int i = 0; i < 30; i++) {
//            String message = "hello work queue rabbitmq " + i;
//            amqpTemplate.convertAndSend("test_work_queue", message);
//            System.out.println("发送的消息内容为：" + message);
//        }
//    }

//    @Test
//    public void testFanoutExchange(){
//        String message = "hello fanout exchange";
//        amqpTemplate.convertAndSend("exchange_fanout", "", message);
//        System.out.println("发送的消息内容为：" + message);
//    }

    @Test
    public void testRouteExchange() {
        String infoMessage = "hello route exchange info";
        amqpTemplate.convertAndSend("exchange_route_name", "info", infoMessage);
        System.out.println("发送的内容为：" + infoMessage);

        String errorMessage = "hello route exchange debug";
        amqpTemplate.convertAndSend("exchange_route_name", "debug", errorMessage);
        System.out.println("发送的内容为：" + errorMessage);

    }

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void handleRedisException(Exception e) {
        if (e instanceof RedisConnectionFailureException || e instanceof JedisConnectionException) {
            log.warn("redis连接失败，请检查redis配置");
        } else {
            log.warn("redis操作失败：{}", e.getMessage());
        }
    }

    /**
     * 操作redis字符串数据类型
     */
    @Test
    public void testRedisStringSave() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        try {
            valueOperations.set("111", "zhangpeng");
            stringRedisTemplate.expire("111", 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            handleRedisException(e);
        }
    }

    /**
     * 操作redis hash数据类型
     */
    @Test
    public void testRedisHashSave() {
        City city = new City();
        city.setId(1L);
        city.setCityName("南京");
        city.setProvinceId(2);
        city.setDescription("nanjing");
//
//        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(City.class));
//        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
//        hashOperations.put("city", "nanjing", city);
        Map<String, City> stringMaps = new HashMap<>();
        stringMaps.put("key1", city);
        stringMaps.put("key2", city);
        stringMaps.put("key3", city);

        redisTemplate.opsForHash().putAll("mapKey", stringMaps);

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        City mapValue = (City) hashOperations.get("mapKey", "key1");
        log.info("hash map value:{}", mapValue.toString());

        Map<String, City> cityMap = redisTemplate.opsForHash().entries("mapKey");
        cityMap.entrySet().stream().forEach(x -> {
            log.info("key值为：{}", x.getKey());
            City c = x.getValue();
            log.info("value值为：{}", c.toString());
        });

    }

    @Test
    public void testRedisSetSave() {
        Set<City> citySet = new HashSet<>();
        City city = new City();
        city.setId(1L);
        city.setCityName("nanjing");
        city.setProvinceId(2);
        city.setDescription("xxxx");
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(City.class));
//        Long set = redisTemplate.opsForSet().add("city", citySet);
//        log.info("set：{}", set);
//
//        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
//        Set<Object> cities = setOperations.members("city");
//        cities.stream().forEach(System.out::println);

//        Set<String>set1=new HashSet<String>();
//        set1.add("set1");
//        set1.add("set2");
//        set1.add("set3");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForSet().add("set3", citySet);
//        redisTemplate.expire("set3", 1, TimeUnit.MINUTES);
//        redisTemplate.rename("set3", "3set");

        Set<City> setCitys = redisTemplate.opsForSet().members("set3");
        Iterator<City> iterator = setCitys.iterator();
        while (iterator.hasNext()) {
            City city1 = iterator.next();
            log.info("city:{}", city1);
        }
    }

    /**
     * 存储key-value值的案例介绍：
     */
    @Test
    public void testKeyValueSave() {
        List<City> cityList = new ArrayList<>();

        City city = new City();
        city.setId(1L);
        city.setCityName("nanjing");
        city.setProvinceId(2);
        city.setDescription("xxxx");

        cityList.add(city);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("cityList", cityList);
        List<City> citys = (List<City>) redisTemplate.opsForValue().get("cityList");
        citys.stream().forEach(System.out::println);
    }

    @Test
    public void testRedisListSave() {
        Set<String> list1 = new HashSet<>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

//        List<String> list2=new ArrayList<>();
//        list2.add("b1");
//        list2.add("b2");
//        list2.add("b3");

        redisTemplate.opsForList().leftPush("listset1", list1);

        Set<String> stringSet = (Set<String>) redisTemplate.opsForList().leftPop("listset1");
//        redisTemplate.opsForList().rightPush("listkey2",list2);

//        List<String> resultList1=(List<String>)redisTemplate.opsForList().leftPop("listkey1");
//        List<String> resultList2=(List<String>)redisTemplate.opsForList().rightPop("listkey2");
//
//        System.out.println("resultList1:"+resultList1);
        System.out.println("stringSet:" + stringSet);
    }

    @Test
    public void testRedisZsetSave() {
        redisTemplate.opsForZSet().add("key1", "value1", 20);
        redisTemplate.opsForZSet().add("key1", "value2", 22);
        redisTemplate.opsForZSet().add("key1", "value3", 17);

        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> stringSet = zSetOperations.range("key1", 0, 1);
        stringSet.stream().forEach(System.out::println);

    }


}
