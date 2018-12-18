package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.LeaveMessageWriteMapper;
import com.bquan.entity.mysql.LeaveMessage;


/**
 * 留言 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public class LeaveMessageWriteServiceImpl extends BaseWriteServiceImpl<LeaveMessage> implements LeaveMessageWriteService{

	@Resource
	private LeaveMessageWriteMapper leaveMessageWriteMapper;

	@Override
	public LeaveMessageWriteMapper getBaseWriteMapper() {
		return leaveMessageWriteMapper;
	}
	
}