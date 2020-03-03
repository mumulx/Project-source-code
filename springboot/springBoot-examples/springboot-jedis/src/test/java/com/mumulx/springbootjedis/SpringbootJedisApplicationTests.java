package com.mumulx.springbootjedis;

import com.mumulx.springbootjedis.entity.User;
import com.mumulx.springbootjedis.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class SpringbootJedisApplicationTests {

    /*    @Autowired
        private JedisPool jedisPool;
        @Test
        void contextLoads() {
            System.out.println(jedisPool);
        }*/
    @Autowired
    private UserService userService;
    /*
    * 模拟操作Jedis操作Redis String类型的数据
    *
    * */
    @Test
    void testRS(){

        String rs =userService.getString("name");
        System.out.println(rs);

    }
    @Test
    void t3(){
        User usr = userService.selectById("zs");
        System.out.println(usr);
    }
















}
