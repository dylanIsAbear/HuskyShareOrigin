package com.huskyshare.backend.config.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test()
    public void test() throws Exception{
        redisTemplate.opsForValue().set("aaa", "111");
        System.out.println("111".equals(stringRedisTemplate.opsForValue().get("aaa")));
    }

}
