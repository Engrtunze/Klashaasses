package com.countriesdata.assessment.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisUtility {
    @Value("${spring.redis.timeout.value}")
    private int REDIS_TIMEOUT;
    private final RedisTemplate<String, String> redisTemplate;

    public void setValue(final String key, final String value){
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, REDIS_TIMEOUT, TimeUnit.DAYS);
    }
    public String getValue(final String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void deleteKeyFromRedis(String key){
        redisTemplate.delete(key);
    }
}
