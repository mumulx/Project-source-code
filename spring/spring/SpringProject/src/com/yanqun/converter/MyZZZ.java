package com.yanqun.converter;

import com.yanqun.entity.Orange;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyZZZ implements BeanDefinitionRegistryPostProcessor {


    //继承自BeanFactoryPostProcessor的方法    （bean的工厂）
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("【a后】postProcessBeanFactory:容器中注册的bean的数量:"+beanFactory.getBeanDefinitionCount());
        Object myBean = beanFactory.getBean("myBean");
        System.out.println( myBean.getClass().getName() );

    }

//    ApplicationListener，

    //BeanDefinitionRegistryPostProcessor接口自己的方法  （维护着容器中所有bean的注册信息）
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("【a先】postProcessBeanDefinitionRegistry:容器中注册的bean的数量:"+registry.getBeanDefinitionCount());

        //额外增加一个：postProcessBeanDefinitionRegistry （可以为容器 额外增加一些bean的注册）
        //Orange
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Orange.class);//产生BeanDefinition
//        beanDefinitionBuilder.getBeanDefinition();;//AbstractBeanDefinition

        registry.registerBeanDefinition("myBean", beanDefinitionBuilder.getBeanDefinition());


    }
}
