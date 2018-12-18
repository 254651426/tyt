package com.bquan.dao.read;

import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.User;

public interface UserReadMapper extends BaseReadMapper<User>{
	
	public Integer count(Map<String,Object> map);
	
	public List<User> selectFmcode(Map<String,Object> map);
}
