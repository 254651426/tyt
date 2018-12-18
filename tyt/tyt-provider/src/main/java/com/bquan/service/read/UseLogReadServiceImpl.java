package com.bquan.service.read;

import javax.annotation.Resource;
import com.bquan.entity.mysql.UseLog;
import com.bquan.service.read.UseLogReadService;
import com.bquan.dao.read.UseLogReadMapper;
import com.bquan.service.read.BaseReadServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 用户日志 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2018-06-08
 */
public class UseLogReadServiceImpl extends BaseReadServiceImpl<UseLog> implements UseLogReadService{

	@Resource
	private UseLogReadMapper useLogReadMapper;

	@Override
	public UseLogReadMapper getBaseReadMapper() {
		return useLogReadMapper;
	}
	
}



