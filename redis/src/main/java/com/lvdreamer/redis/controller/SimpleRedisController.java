package com.lvdreamer.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class SimpleRedisController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/set")
    public String setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        Object result = redisTemplate.opsForValue().get(key);
        return String.valueOf(result);
    }

    @RequestMapping("/get")
    public String getString(String key) {
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }
}
