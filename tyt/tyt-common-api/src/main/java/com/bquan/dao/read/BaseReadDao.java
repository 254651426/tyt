package com.bquan.dao.read;

import java.util.List;
import java.util.Map;

public interface BaseReadDao<T> {

	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
