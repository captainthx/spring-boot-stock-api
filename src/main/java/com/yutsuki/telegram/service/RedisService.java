package com.yutsuki.telegram.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, 2, java.util.concurrent.TimeUnit.HOURS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


}
