package com.bquan.service.read;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bquan.bean.Pager;
import com.github.pagehelper.PageInfo;

public interface BaseReadService<T> {

	public T get(String id);
	
	public List<T> getAllList();
	
	public List<T> getTargetList(Map<String,Object> map);
	
	public Integer count();
	
	public Integer count(Map<String,Object> map);
	
	public BigDecimal sum(Map<String,Object> map);
	
	public PageInfo<T> findPage(int pageNum, int pageSize,Map<String,Object> map);
	
	public Pager findPager(Pager pager);
	
	public Pager findPager(Pager pager,Map<String,Object> map);
}
