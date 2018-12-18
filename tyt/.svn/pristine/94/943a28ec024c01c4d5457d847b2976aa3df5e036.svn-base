package com.bquan.job;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bquan.service.write.UserWriteService;
import com.bquan.util.JDBCHelper;

@Component
@Lazy(false)
@EnableScheduling
public class ShadowsocksJob {
	
	@Autowired
	private UserWriteService userWriteService;

	@Scheduled(cron = "0 0 0 * * ?")
	public void accountJob() {
		
		userWriteService.generateShadowsocks();
        System.out.println("shadowsocks配置已经更新");
        
    }
}
