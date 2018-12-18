package com.bquan.service.write;

import java.util.List;
import java.util.Map;

public interface BaseWriteService<T> {

	public int insert(T t);
	
	public int update(T t);
	
	public int delete(T t);
	
	public int delete(String id);
	
	public int deleteBatch(String [] ids);
}
