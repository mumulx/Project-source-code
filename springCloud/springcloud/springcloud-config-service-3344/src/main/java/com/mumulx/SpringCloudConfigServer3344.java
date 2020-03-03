package com.mumulx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer3344 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServer3344.class, args);
    }
}
