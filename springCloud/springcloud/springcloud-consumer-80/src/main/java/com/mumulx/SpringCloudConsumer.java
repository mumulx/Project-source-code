package com.mumulx;


import com.myrule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//Ribbon和Eureka整合以后，客户端可以直接调用，不用关心Ip地址和端口号
@SpringBootApplication
@EnableEurekaClient//启动
//@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = MyRule.class)//在微服务启动的时候就能去加载我们自定义的Ribbon类
public class SpringCloudConsumer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumer.class, args);
    }
}
