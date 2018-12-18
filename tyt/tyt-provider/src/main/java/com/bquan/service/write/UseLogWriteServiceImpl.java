package com.bquan.service.write;

import javax.annotation.Resource;
import com.bquan.entity.mysql.UseLog;
import com.bquan.service.write.UseLogWriteService;
import com.bquan.dao.write.UseLogWriteMapper;
import com.bquan.service.write.BaseWriteServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 用户日志 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2018-06-08
 */
public class UseLogWriteServiceImpl extends BaseWriteServiceImpl<UseLog> implements UseLogWriteService{

	@Resource
	private UseLogWriteMapper useLogWriteMapper;

	@Override
	public UseLogWriteMapper getBaseWriteMapper() {
		return useLogWriteMapper;
	}
	
}