package com.example.quartz;

import lombok.Setter;
import org.quartz.*;

import java.time.LocalDateTime;

/**
 * DisallowConcurrentExecution 禁止并发执行
 * PersistJobDataAfterExecution JobDetail 中的数据在同一 Job 中共享
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class BosJob implements Job {

    @Setter
    private String name;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("name : " + name);
        System.out.println("BosJob execute : " + LocalDateTime.now());
    }
}
