package com.huskyshare.backend.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@Component
@Scope(scopeName = "singleton")
public class RedisHandler {

    public static JedisPool jedisClient = new JedisPool(new JedisPoolConfig(), "localhost");

    public void store(String key, String value){
        jedisClient.getResource().set(key, value);
    }

    public String get(String key){
        return jedisClient.getResource().get(key);
    }

    public void storeList(String index, String[] value){
        for(String v : value)   jedisClient.getResource().lpush(index, v);
    }

    public void storeList(String index, String value){
        jedisClient.getResource().lpush(index, value);
    }

    public List<String> getList(String key, int length){
        return jedisClient.getResource().lrange(key, 0 , length);
    }

    public Jedis getJedis(){
        return jedisClient.getResource();
    }

    @Bean
    public RedisHandler getRedisHandler(){
        return new RedisHandler();
    }

}
