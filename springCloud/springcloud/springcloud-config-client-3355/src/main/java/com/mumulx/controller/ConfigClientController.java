package com.mumulx.controller;
/**
 * 写这个controller这是为了证明客户端能通过服务端从gitee拿到数据
 * 这里把数据打印出来了
 * 可以不写这个controller，在bootstrap.yml和application.yml写配置就行了
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig() {
        return "applicationName:"+applicationName+
                "eurekaServer:"+eurekaServer+
                "port:"+port;
    }
}
