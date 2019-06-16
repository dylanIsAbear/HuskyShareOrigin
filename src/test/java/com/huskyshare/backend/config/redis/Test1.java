package com.huskyshare.backend.config.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class Test1 extends RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("aaa", "bbb");
        Assert.assertEquals(redisTemplate.opsForValue().get("aaa"), "bbb");
    }

}
