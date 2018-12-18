package com.bquan.controller.sys;

import com.bquan.util.PageUtils;
import com.bquan.util.Query;
import com.bquan.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bquan.entity.mysql.ScheduleJobLogEntity;
import com.bquan.service.read.ScheduleJobLogReadService;
import com.bquan.service.write.ScheduleJobLogWriteService;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:39:52
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	
	@Autowired
	private ScheduleJobLogReadService scheduleJobLogReadService;
	@Autowired
	private ScheduleJobLogWriteService scheduleJobLogWriteService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ScheduleJobLogEntity> jobList = scheduleJobLogReadService.queryList(query);
		int total = scheduleJobLogReadService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogReadService.queryObject(logId);
		
		return R.ok().put("log", log);
	}
}
