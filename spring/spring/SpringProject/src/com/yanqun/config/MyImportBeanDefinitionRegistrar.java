package com.yanqun.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        "com.yanqun.entity.Orange"
//        BeanDefinition beanDefinition =  new RootBeanDefinition(Orange.class) ;
        BeanDefinition beanDefinition =  new RootBeanDefinition("com.yanqun.entity.Orange") ;
        registry.registerBeanDefinition("myorange", beanDefinition ); // id ,class

    }
}
