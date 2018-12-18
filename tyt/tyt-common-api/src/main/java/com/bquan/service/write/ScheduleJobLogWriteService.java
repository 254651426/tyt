package com.bquan.service.write;

import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:34:48
 */
public interface ScheduleJobLogWriteService {
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
