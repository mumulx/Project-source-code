package com.yanqun.job;

import com.yanqun.entity.ScheduleJob;
import com.yanqun.service.MeetingService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PlanJob  implements Job {
        private MeetingService  meetingService = new MeetingService() ;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        meetingService.remindMeeting(); ;
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        ScheduleJob job =  (ScheduleJob)jobDataMap.get("scheduleJob");
        System.out.println(job.getJobId());
        System.out.println(job.getJobName());
    }
}
