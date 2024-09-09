package com.zzw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest//如果在测试类中加入这个注解，会在将来单元测试时，会初始化Spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet(){
        //往redis中存入一个键值对，SpringRedisTemplate
        ValueOperations<String,String> operations =stringRedisTemplate.opsForValue();
        operations.set("username","111");
        operations.set("id","1",8, TimeUnit.SECONDS);
    }
    @Test
    public void testGet(){
        //往redis中存入一个键值对，SpringRedisTemplate
        ValueOperations<String,String> operations =stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
    }
}
