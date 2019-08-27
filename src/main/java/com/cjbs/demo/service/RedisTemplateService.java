package com.cjbs.demo.service;

import com.cjbs.demo.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author shj
 */
@Service
public class RedisTemplateService {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplateService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 简单测试Redis
     */
    public void test() {
        redisTemplate.opsForValue().set("study","redis");
        System.out.println(this.redisTemplate.opsForValue().get("study"));
    }

    /**
     * 测试Redis缓存对象  序列化
     */
    public void testSerializableRedis() {
        User user = new User();
        user.setName("shj");
        user.setAge("23");

        redisTemplate.opsForValue().set("user",user);
        System.out.println(this.redisTemplate.opsForValue().get("user"));
    }
}
