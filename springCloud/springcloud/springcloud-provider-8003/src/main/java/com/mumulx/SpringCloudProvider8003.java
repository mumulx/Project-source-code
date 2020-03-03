package com.mumulx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient//在服务启动后自动注册到eureka
@SpringBootApplication
@EnableDiscoveryClient//服务发现
public class SpringCloudProvider8003 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProvider8003.class, args);
    }

    //增加一个servlet,将8001服务注册到dashboard流监控
/*    @Bean
    public ServletRegistrationBean a() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }*/
}
