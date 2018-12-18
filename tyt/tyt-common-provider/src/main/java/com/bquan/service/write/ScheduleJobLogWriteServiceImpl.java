package com.bquan.service.write;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bquan.dao.write.ScheduleJobLogWriteDao;
import com.bquan.entity.mysql.ScheduleJobLogEntity;
import com.bquan.service.write.ScheduleJobLogWriteService;

public class ScheduleJobLogWriteServiceImpl implements ScheduleJobLogWriteService {
	@Autowired
	private ScheduleJobLogWriteDao scheduleJobLogDao;
	
	@Override
	public void save(ScheduleJobLogEntity log) {
		scheduleJobLogDao.save(log);
	}

}
