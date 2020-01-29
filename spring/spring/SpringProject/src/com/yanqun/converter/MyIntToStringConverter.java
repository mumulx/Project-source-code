package com.yanqun.converter;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(value="myConverter")//@Server  @COntroller @Repository
public class MyIntToStringConverter {

    @PostConstruct
    public void init(){
        System.out.println("转换..Init...");
    }

    public void myConverter(){
        System.out.println("转换.......");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("转换..destroy...");
    }
}
