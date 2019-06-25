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

import java.util.concurrent.TimeUnit;

@Component
@Scope(scopeName = "singleton")
public class RedisHandler {

    private static RedisHandler handler = new RedisHandler();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void store(String key, String value){
        redisTemplate.opsForValue().set(key, value, 60*10, TimeUnit.SECONDS);
    }

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @Bean
    public RedisHandler getRedisHandler(){
        return handler;
    }

}
