package com.yanqun.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

//监听器
//@Component
public class MyListener implements ApplicationListener {
    //监听对象
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("========**********========"+event+"======*********==========");
    }
}
