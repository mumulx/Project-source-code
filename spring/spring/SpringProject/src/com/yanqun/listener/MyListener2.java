package com.yanqun.listener;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener2 {

    //本方法是一个 监听方法
    @EventListener(classes = {ApplicationEvent.class})
    public void myListenerMethod(ApplicationEvent event){
        System.out.println("--0000000--------"+event);

    }


}
