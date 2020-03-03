package com.mumulx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//启动之后访问 http://localhost:7001/
@SpringBootApplication
@EnableEurekaServer         //EnableEurekaServer 服务端启动类，可以接受别人注册进来

public class SpringCloudEureka7001 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEureka7001.class,args);
    }
}
