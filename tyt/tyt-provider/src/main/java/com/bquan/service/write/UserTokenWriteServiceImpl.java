package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.UserTokenWriteMapper;
import com.bquan.entity.mysql.UserToken;


/**
 * 在线token Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public class UserTokenWriteServiceImpl extends BaseWriteServiceImpl<UserToken> implements UserTokenWriteService{

	@Resource
	private UserTokenWriteMapper userTokenWriteMapper;

	@Override
	public UserTokenWriteMapper getBaseWriteMapper() {
		return userTokenWriteMapper;
	}
	
}