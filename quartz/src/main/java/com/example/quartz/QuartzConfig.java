package com.example.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder
                .newJob(QuartzJob.class)
                .withIdentity("hello", "hello")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail())
                .withIdentity("hello", "hello")
                .withSchedule(SimpleScheduleBuilder
                        .repeatHourlyForever().withIntervalInSeconds(1))
                .build();
    }


}
