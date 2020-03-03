package com.mumulx.service;


import com.mumulx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /*
    redis-->lettuce-->RedisTemplate(Jedis)进一步的封装
    RedisTemplate方法和命令肯定不一样

     * Redis String 类型
     * 需求：用户输入一个key
     *先判断Redis中是否存在该数据，
     * 如果存在，在redis中进行查询，并返回
     * 如果不存在，在MySql数据库进行查询，将结果献给Redis，并返回
     * */
    public String getString(String key){
        String val ;

        if (redisTemplate.hasKey(key)) {
           val = (String) redisTemplate.opsForValue().get(key);
           log.info("=======redis中查询");
        }else {
            val = "zxzzzz";
            log.info("===mysql");
            redisTemplate.opsForValue().set(key,val);
            log.info("========放入redis");
        }


        return val;
    }

    /**
     * 测试String类型
     * 需求：用户输入一个Redis数据，该key的有效期为24小时
     */
    public void expireStr(String key, String val) {
        redisTemplate.opsForValue().set(key,val);
        redisTemplate.expire(key, 28, TimeUnit.SECONDS);
    }



    /*测试Hash类型的数据
    *
    * 存一个对象
    * 需求分析
    *
    *需求分析:根据用户ID 查询用户信息
        用户在前端传入一个ID编写，
        根据用户的ID查询用户的对象信息。
        先判断如果Redis中存在，直接返回给用户结果，并返回
        如果Redis中不存在，查询MYSOL， 并结查询的结果赋值给Redis并返回

    * */

    public User selectById(String id){
        User usr =null;
        if (redisTemplate.opsForHash().hasKey("user", id)) {
            return (User)redisTemplate.opsForHash().get("user",id);
        }else {

            log.info("=======查询数据库");
            usr= new User("001","zs",12);
            redisTemplate.opsForHash().put("user",usr.getId(),usr);
            log.info("========放入reids");
        }
        return usr;
    }







}
