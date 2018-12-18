package com.bquan.service.read;

import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.ScheduleJobEntity;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月28日 上午9:55:32
 */
public interface ScheduleJobReadService {

	/**
	 * 根据ID，查询定时任务
	 */
	ScheduleJobEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJobEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
}
