package com.yanqun.job;

import com.yanqun.service.MeetingService;
import org.quartz.*;

import java.util.List;

public class PlanJob implements Job {

    MeetingService meetingService = new MeetingService();
    //jobExecutionContext可以获取 设置的各种参数值
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TriggerKey triggerkey =
                jobExecutionContext.getTrigger().getKey();

        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println("----");
        System.out.println(triggerkey+"\n"+jobKey);

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        List<String> infos = (List<String>)jobDataMap.get("infos");
        System.out.println(infos);

        //存放 计划执行的任务...
        meetingService.calClassMeeting();
    }
}
