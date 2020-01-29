package com.yanqun.test;

import com.yanqun.job.PlanJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestQuartz {
//XxxBuilder ->withIdentity（）-->Xxx
    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
//        PlanJob
        JobBuilder jobBuilder = JobBuilder.newJob(PlanJob.class);//PlanJob PlanJob PlanJob
        //产生实际使用的Job
        JobDetail jobDetail = jobBuilder.withIdentity("meeting Job", "group1").build();

        //向Job的execute()中传入一些参数。。。
//        JobDatMap
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        List<String> names = Arrays.asList(new String[]{"zs","ls","ww"});
        jobDataMap.put("infos",names);

        // 触发器(Trigger)：时间规则  ，依赖2个对象(TriggerBuilder ,Scheduel)
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder = triggerBuilder.withIdentity("meeting trigger", "group1");
        triggerBuilder.startNow();//当满足条件时  立刻执行
        // 2019-03-13 09:46:30   --  // 2019-03-13 09:46:45

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse("2019-03-13 09:46:30");
        Date end = sdf.parse("2019-03-13 09:46:45");

//        triggerBuilder.startAt(start);
//        triggerBuilder.endAt(end);

        //scheduelBuilder：定执行的周期（时机）
//        SimpleScheduleBuilder scheduelBuilder = SimpleScheduleBuilder.simpleSchedule();
//        scheduelBuilder.withIntervalInSeconds(1) ;//每隔1秒执行一次
//        scheduelBuilder.withRepeatCount(300) ;//重复执行3次

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("5,10,15,30,45 * * * * ? *");


        //产生触发器
        CronTrigger trigger = triggerBuilder.withSchedule(cronScheduleBuilder).build();


//        调度器（工厂产生调度器）
         SchedulerFactory secheduleFacotry = new StdSchedulerFactory();
        //产生调度器
        Scheduler scheduler = secheduleFacotry.getScheduler();

        //通过调度器 将 任务 和 触发器一一对应
        scheduler.scheduleJob(jobDetail,trigger) ;
        scheduler.start();



//        scheduler.shutdown(true);
    }
}
