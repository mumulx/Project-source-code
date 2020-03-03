package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyRule {
    //规则，轮询
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
