package com.example.quartz;

import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

class BosJobTest {

    @SneakyThrows
    public static void main(String[] args) {
        JobDetail detail = JobBuilder
                .newJob(BosJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("name", "张三")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "trigger1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(detail, trigger);
        scheduler.start();
    }
}