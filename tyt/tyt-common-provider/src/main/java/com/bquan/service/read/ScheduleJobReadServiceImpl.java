package com.bquan.service.read;

import com.bquan.util.Constant.ScheduleStatus;
import com.bquan.util.ScheduleUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bquan.dao.read.ScheduleJobReadDao;
import com.bquan.entity.mysql.ScheduleJobEntity;
import com.bquan.service.read.ScheduleJobReadService;

public class ScheduleJobReadServiceImpl implements ScheduleJobReadService {
//	@Autowired
//    private Scheduler scheduler;
	@Autowired
	private ScheduleJobReadDao schedulerJobReadDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobEntity> scheduleJobList = schedulerJobReadDao.queryList(new HashMap<String, Object>());
		for(ScheduleJobEntity scheduleJob : scheduleJobList){
//			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
//            //如果不存在，则创建
//            if(cronTrigger == null) {
//                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
//            }else {
//                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
//            }
		}
	}
	
	@Override
	public ScheduleJobEntity queryObject(Long jobId) {
		return schedulerJobReadDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
		return schedulerJobReadDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return schedulerJobReadDao.queryTotal(map);
	}

}
