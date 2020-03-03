package com.mumulx.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
/*    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
   */
    //负载均衡实现RestTemplate
    @Bean
    @LoadBalanced       //Reibbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }




//规则，轮询
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }





















}
