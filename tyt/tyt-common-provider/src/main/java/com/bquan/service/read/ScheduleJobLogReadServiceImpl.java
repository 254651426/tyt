package com.bquan.service.read;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bquan.dao.read.ScheduleJobLogReadDao;
import com.bquan.entity.mysql.ScheduleJobLogEntity;
import com.bquan.service.read.ScheduleJobLogReadService;

public class ScheduleJobLogReadServiceImpl implements ScheduleJobLogReadService {
	@Autowired
	private ScheduleJobLogReadDao scheduleJobLogDao;
	
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return scheduleJobLogDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogDao.queryTotal(map);
	}

}
