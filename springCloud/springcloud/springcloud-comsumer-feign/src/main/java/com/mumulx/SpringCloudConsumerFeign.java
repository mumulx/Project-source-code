package com.mumulx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//Ribbon和Eureka整合以后，客户端可以直接调用，不用关心Ip地址和端口号
@SpringBootApplication
@EnableEurekaClient//启动
@EnableFeignClients(basePackages = "com.mumulx")
public class SpringCloudConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerFeign.class, args);
    }
}
/*
*
*服务熔断:服务端某 个服务超时或者异常，引起熔脚，保险丝~

服务降级:客户端从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用此时在客户端，我们可以准备一个FallbackFactory,返回一个默认的值( 缺省值)，整体的服务水平下降了但是，好歹能用

* */







