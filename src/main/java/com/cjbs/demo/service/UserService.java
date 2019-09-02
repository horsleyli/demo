package com.cjbs.demo.service;


import com.cjbs.demo.domain.User;
import com.cjbs.demo.repository.UserRepository;
import com.cjbs.demo.web.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author shj
 */
@Service
public class UserService {

    private UserRepository userRepository;

    private RedisTemplate redisTemplate;

    private ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, RedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 根据姓名查询年龄
     * @param name name
     * @return User
     */
    public User getAgeByName(String name) {
        Optional<User> userOptional = userRepository.getAgeByName(name);
        User user = null;
        if(userOptional.isPresent()) {
            user = userOptional.get();
        }
        return user;
    }

    /**
     * 添加用户
     * @param userDTO userDTO
     */
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        User createUser = userRepository.save(user);
        return new UserDTO(createUser);
    }


//    redis缓存测试
//    1.@CacheConfig(cacheNames="users") 注解指的是该类中的缓存的名称都是users
//    2.@CachePut()中userCache要加‘’单引号，表示这是一个字符串。
//    3.@Cacheable能够根据方法的请求参数对其结果进行缓存(缓存的是方法的返回结果)，一般用于insert()操作
//    4.@CachePut(key="'userCache'")主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用，一般用于update()操作
//    5.@CacheEvict(key="'userCache'")主要针对方法配置，能够根据一定的条件对缓存进行清空，一般用于delete（）操作
//    6.本例中的@Cacheable和@CachePut和@CacheEvict的key值必须都是同一个缓存的key，因为这样当update的时候缓存的时候，get方法的得到的才是最新数据，而当删除的时候@CacheEvict，也必须把该key的缓存删除。

//注解模式、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
    /**
     * 查询数据并将数据存储到Redis
     * @param id id
     * @return User
     */
    @Cacheable(value = "user", key = "#p0")
    public User getUserById(String id) {
        System.out.println("执行这里，说明缓存中读取不到数据，直接读取数据库......");
        return userRepository.findById(id).get();
    }

    /**
     * 更新数据并更新Redis
     * @param userDTO userDTO
     * @return User
     */
    @CachePut(value = "user", key = "#p0")
    public User updateUser(UserDTO userDTO) {
        System.out.println("执行这里，更新数据库，更新缓存......");
        return userRepository.save(userDTO.ToEntity());
    }

    /**
     * 删除数据并删除缓存Redis
     * @param id id
     */
    @CacheEvict(value = "user", key = "#p0")
    public void deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user.toString());
            userRepository.delete(user);
        }
    }
//手动添加缓存、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
    /**
     * 查询数据并将数据存储到Redis
     * @param age age
     * @return User
     */
    public List<User> getAllByAgeHand(String age) throws IOException {
        String redisKey = "userRedis" + age;
        List<User> users = null;
        if(redisTemplate.opsForSet().members(redisKey).isEmpty()){
            System.out.println("执行这里，说明缓存中读取不到数据，直接读取数据库......");
            users = userRepository.getAllByAge(age).get();
            for(User user : users) {
                //序列化user对象
                String jsonString = objectMapper.writeValueAsString(user);
                redisTemplate.opsForSet().add(redisKey, jsonString);
            }
            //设置key过期时间1800s
            redisTemplate.expire(redisKey, 1800, TimeUnit.SECONDS);
        } else {
            System.out.println("执行这里，说明缓存中有数据，读取缓存数据......");
            Set set= redisTemplate.opsForSet().members(redisKey);
            List<String> list =  new ArrayList<>(set);
            for(String str : list) {
                //反序列化user对象
                User user = objectMapper.readValue(str, User.class);
                users.add(user);
            }
        }

        return users;
    }


}
