package com.yanqun.test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;

public class TestQuartzSpring {
    public static void main(String[] args) throws SchedulerException {
        //singleton:容器初始化时
     ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        StdScheduler scheduler = (StdScheduler)context.getBean("schedulerFactoryBean");
//        scheduler.start();SchedulerFactoryBean默认会在实例化时自动启动(singleton的bean会会在ioc容器初始化时 自动加载)
    }
}
