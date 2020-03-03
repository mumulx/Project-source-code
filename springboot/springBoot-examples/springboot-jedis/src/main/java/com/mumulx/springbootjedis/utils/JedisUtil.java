package com.mumulx.springbootjedis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    // 获取Jedis资源
    public Jedis getJedis() {

        return jedisPool.getResource();
    }

    //释放Jedis连接
    public void close(Jedis jedis){
        if (jedis!=null){
            jedis.close();
        }
    }
    //封装工具类？
    /*
    * redis有什么功能，jedis就有什么方法，但是这些方法的功能可能没有那么强大
    *
    *
    * */


    public long caclTimeHour(int hours){

        long num = hours*60*60;
        return num;
    }




}
