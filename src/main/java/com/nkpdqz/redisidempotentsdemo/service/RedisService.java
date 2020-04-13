package com.nkpdqz.redisidempotentsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean set(final String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean setEx(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public Object get(final String key) {
        Object result = null;
        result = redisTemplate.opsForValue().get(key);
        return result;
    }

    public boolean remove(final String key) {
        if (exists(key)) {
            Boolean delete = redisTemplate.delete(key);
            return delete;
        }
        return false;
    }
}
