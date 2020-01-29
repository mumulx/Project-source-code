package com.yanqun.converter;

import com.yanqun.entity.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;

@Controller //(4个)
public class MyXxx implements BeanPostProcessor {

    //拦截器
    @Override//bean:Student(zs)
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("初始化:"+beanName+":"+bean);
//        bean.setName("ls")

        if(bean instanceof Student){
            System.out.println("MyXxx...初始化..");
            Student stu = (Student)bean ;
            stu.setStuName("zs123456");
            stu.setStuNo(123);
            return stu ;
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student) {
//            System.out.println("销毁:" + beanName + ":" + bean);
            System.out.println("MyXxx...销毁..");
        }
            return bean;
    }
}
