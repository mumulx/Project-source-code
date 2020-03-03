package com.mumulx.springbootjedis.service.impl;

import com.mumulx.springbootjedis.entity.User;
import com.mumulx.springbootjedis.service.UserService;
import com.mumulx.springbootjedis.utils.JedisUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service
@Log //与private Logger logger = LoggerFactory.getLogger(JedisConfig.class);作用相同
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisPool jedisPool;//JedisPool Jedis连接池
    /*
    Redis中有什么命令，Jedis中就有什么方法
     * Redis String 类型
     * 需求：用户输入一个key
     *先判断Redis中是否存在该数据，
     * 如果存在，在redis中进行查询，并返回
     * 如果不存在，在MySql数据库进行查询，将结果献给Redis，并返回
     * */
    @Override
    public String getString(String key) {
        String val =null;
        //1. 得到Jedis对象
        Jedis jedis= jedisPool.getResource();
        //2.判断key是否存在Redis
        if (jedis.exists(key)){
            log.info("查询Redis中的数据");
           val =  jedis.get(key);
        }else{
            log.info("查询的是MYSQL数据库");
            val = "zxxx";
            val = jedis.set(key,val);
        }
        //3. 关闭连接
        jedis.close();
        return val;
    }

    @Autowired
    private JedisUtil jedisUtil;
    /**
     *
     *测试String类型
     * 需求：用户输入一个Redis数据，该key的有效期为24小时
     * */
    public void expriseString (String key,String value){
        Jedis jedis = jedisUtil.getJedis();
        jedis.set(key, value);
//        jedisUtil.caclTimeHour(24)
        jedis.expire(key,20);//以秒为单位
        log.info(key+"\t设置值"+value);
        jedisUtil.close(jedis);

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
        如果Redis中不存在，查询IYSOL， 并结查询的结果赋值给Redis并返回

    * */

public User selectById(String id){
    String key = "user"+id;
    User usr = new User();
    //获取Jedis对象
    Jedis jedis= jedisUtil.getJedis();
    //实现逻辑判断
    if(jedis.exists(key)){
        Map<String, String> map = jedis.hgetAll(key);

        usr.setName(map.get("name"));
        usr.setAge(Integer.parseInt(map.get("age")));
        usr.setId(map.get("id"));
        log.info("=======查询的是Redis");

    }else {
        //不存在
        log.info("查询的是MySQL数据库");

        usr.setAge(12);
        usr.setId("123");
        usr.setName("zs");
        //存入redis
        Map<String,String> map =new HashMap<>();
        map.put("name", usr.getName());
        map.put("age", usr.getAge()+"");
        map.put("id", usr.getId());
        jedis.hmset(key, map);

        log.info("存入redis数据库");

    }
    return usr;
}










}
