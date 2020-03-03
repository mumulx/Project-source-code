package com.mumulx;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 自动配置
* 1. 配置类RabbitAutoConfiguration
* 2.自动配置了连接工厂CachingConnectionFactory
* 3. 封装了rabbitmq的配置RabbitProperties
* 4. RabbitTemplate,给RabbitMQ发送和接收消息
* 5. AmqpAdmin：rabbitMQ的系统管理功能组件
*   AmqpAdmin：创建和删除Queue、Exchange，Binding
*
* 6.@EnableRabbit+@RabbitListener(queues = "mumu.news")监听消息队列中的内容
*
*
*
*
* */

@EnableRabbit//开启基于注解的RabbitMQ模式
@SpringBootApplication
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
