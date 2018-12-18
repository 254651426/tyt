package com.bquan.job;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@Lazy(false)
//@EnableScheduling
public class TestJob {

//	@Scheduled(cron = "0/1 * * * * ?")
	public void TaskJob() {
        System.out.println("定时器的执行。。。。111。");
    }
}
