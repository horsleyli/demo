package com.cjbs.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author shj
 *
 * 实际情况中可能有多种需求，泛型各不相同，比如有<String,User>
 * 如果需要改变key的类型，直接在Config类里面在加一个方法就行
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 设置Redis缓存
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisCacheManager
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        // entryTtl(Duration.ofHours(1)) 设置缓存有效期一小时
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)).cacheDefaults(redisCacheConfiguration).build();
    }


    /**
     * 配置Redis序列化容器
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //关联
        template.setConnectionFactory(redisConnectionFactory);

        //key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());

        //hash的key采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());

        //value的序列化方式采用Jackson
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        //hash的value的序列化方式采用Jackson
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        return template;
    }

    /**
     * 自定义缓存key生成策略
     * @return KeyGenerator
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for(Object obj:params){
                sb.append(obj.toString());
            }
            System.out.println("调用Redis生成key："+sb.toString());
            return sb.toString();
        };
    }

}
