package com.mumulx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuul9527 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuul9527.class, args);
    }
}
