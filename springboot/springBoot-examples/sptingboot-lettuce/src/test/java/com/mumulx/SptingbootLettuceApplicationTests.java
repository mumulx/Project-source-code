package com.mumulx;

import com.mumulx.entity.User;
import com.mumulx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SptingbootLettuceApplicationTests {

    @Autowired
    UserService userService;
/*    @Test
    void contextLoads() {
        userService.getString();
    }*/
    @Test
    void contextLoads1() {
        userService.getString("aa");
    }
    @Test
    void contextLoads2() {
        userService.expireStr("qq","sdfs");
    }
    @Test
    void contextLoads3() {
        System.out.println(userService.selectById("001"));
    }
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k--v都是String的

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    void contextLoads4() {
        //给redis中保存数据
        //stringRedisTemplate.opsForValue().append("msg","hello");
  /*      String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
*/
/*
		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
*/
/*


        User usr= new User("001","zs",12);
        redisTemplate.opsForHash().put("user",usr.getId(),usr);

        User usr1 = (User)redisTemplate.opsForHash().get("user","001");
*/




    }

}
