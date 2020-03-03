package com.mumulx;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    //发送消息
    @Test
    void contextLoads() {
        /*
        * 1.单播(点对点)
        * */
        //message需要自己定义，定义消息体内容
        //rabbitTemplate.send(exchage,routerKey,message);

        //只需要传入要发送的对象，自动序列化发送给rabbitMQ
        //rabbitTemplate.convertAndSend(exchage,routerKey,message);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一条消息");
        map.put("data", Arrays.asList("hello", 123, true));
        //对象被默认序列化之后发送出去content_type:	application/x-java-serialized-object
        rabbitTemplate.convertAndSend("exchange.direct","mumu.news",map);


    }

    //接收消息
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("mumu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    /*
    * 广播发送消息
    * */
    @Test
    public void postmsg() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一条消息");
        //map.put("data", Arrays.asList("hello", 1213, true));
        rabbitTemplate.convertAndSend("exchange.fanout","",map);
    }

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void createExchange(){

        /*amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("====创建成功");*/

/*        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("队列创建成功");*/

    //创建绑定
 /*   amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqpxx",null));
    System.out.println("绑定成功");*/

    }
}
