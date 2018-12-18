package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.dao.read.UserTokenReadMapper;
import com.bquan.entity.mysql.UserToken;


/**
 * 在线token Service层读接口实现
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public class UserTokenReadServiceImpl extends BaseReadServiceImpl<UserToken> implements UserTokenReadService{

	@Resource
	private UserTokenReadMapper userTokenReadMapper;

	@Override
	public UserTokenReadMapper getBaseReadMapper() {
		return userTokenReadMapper;
	}

	public List<UserToken> getByToken(String token) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("token", token);
		return userTokenReadMapper.select(map);
	}

	public List<UserToken> getByUsername(String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("orderBy", "create_date");
		map.put("orderDesc", "asc");
		return userTokenReadMapper.select(map);
	}
	
}



