package com.huskyshare.backend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class RedisHandler {

    private static RedisHandler handler = new RedisHandler();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void stringTest(){
        stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.opsForValue().set("1", "2");
        System.out.println(stringRedisTemplate.opsForValue().get("1"));
    }

    public void store(String key, String value){
        //stringRedisTemplate.opsForValue().set(key, value);
        RedisSerializer redisSerializer =new StringRedisSerializer();
        stringRedisTemplate.setKeySerializer(redisSerializer);
        //ValueOperations<String,String> vo = redisTemplate.opsForValue();
        //vo.set(key, value);
    }

    public String get(String key){
        return "";
    }

    @Bean
    public RedisHandler getRedisHandler(){
        return handler;
    }

}
