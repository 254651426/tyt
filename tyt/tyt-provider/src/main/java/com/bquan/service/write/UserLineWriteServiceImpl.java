package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.UserLineWriteMapper;
import com.bquan.entity.mysql.UserLine;


/**
 * 节点线路 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-24
 */
public class UserLineWriteServiceImpl extends BaseWriteServiceImpl<UserLine> implements UserLineWriteService{

	@Resource
	private UserLineWriteMapper userLineWriteMapper;

	@Override
	public UserLineWriteMapper getBaseWriteMapper() {
		return userLineWriteMapper;
	}
	
}