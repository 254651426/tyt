package com.bquan.service.write;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bquan.dao.write.BaseWriteMapper;


public abstract class BaseWriteServiceImpl<T> implements BaseWriteService<T>{
	
	public abstract BaseWriteMapper<T> getBaseWriteMapper();

	public int insert(T t) {
		return getBaseWriteMapper().insert(t);
	}

	public int update(T t) {
		return getBaseWriteMapper().update(t);
	}

	public int delete(T t) {
		return getBaseWriteMapper().delete(t);
	}
	
	public int delete(String id){
		String [] ids = new String []{id};
		return getBaseWriteMapper().deleteBatch(ids);
	}

	public int deleteBatch(String [] ids){
		return getBaseWriteMapper().deleteBatch(ids);
	}
}
