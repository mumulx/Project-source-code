package com.yanqun.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("myComponent99999")  //id  name
public class MyComponent implements ApplicationContextAware , BeanNameAware {
        private ApplicationContext applicationContext;
        private String beanName ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("000000000000000000000000000000"+applicationContext);
        this.applicationContext= applicationContext ;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("獲取當前bean的name"+name);
        this.beanName = name ;
    }
}
