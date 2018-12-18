package com.bquan.dao.write;

import java.util.Map;

public interface BaseWriteMapper<T> {

	public int insert(T t);
	
	public int update(T t);
	
	public int delete(T t);
	
	public int deleteBatch(String [] ids);
}
