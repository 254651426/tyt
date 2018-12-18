package com.bquan.service.read;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bquan.bean.Pager;
import com.bquan.dao.read.BaseReadMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseReadServiceImpl<T> implements BaseReadService<T>{
	
	
	public abstract BaseReadMapper<T> getBaseReadMapper();

	public T get(String id) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isEmpty(id)){
			return null;
		}else{
			map.put("id", id);
			List<T> list = getBaseReadMapper().select(map);
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}
	}

	public List<T> getAllList() {
		Map<String,Object> map=new HashMap<String,Object>();
		return getBaseReadMapper().select(map);
	}

	public List<T> getTargetList(Map<String,Object> map) {
		return getBaseReadMapper().select(map);
	}
	
	public Integer count(){
		Map<String,Object> map=new HashMap<String,Object>();
		return getBaseReadMapper().count(map);
	}

	public Integer count(Map<String,Object> map){
		return getBaseReadMapper().count(map);
	}
	
	public BigDecimal sum(Map<String,Object> map){
		return getBaseReadMapper().sum(map);
	}
	
	public PageInfo<T> findPage(int pageNum, int pageSize,Map<String,Object> map) {
		PageHelper.startPage(pageNum, pageSize);
    	List<T> list = getBaseReadMapper().select(map);
    	PageInfo<T> pageInfo = new PageInfo(list);
    	return pageInfo;
	}

	public Pager findPager(Pager pager) {
		Map<String,Object> map = new HashMap<String,Object>();
		// 关键字模糊查询
		if(StringUtils.isNotEmpty(pager.getSearchBy())
				&&StringUtils.isNotEmpty(pager.getKeyword())){
			map.put("searchBy", pager.getSearchBy());
			map.put("keyword", pager.getKeyword());
		}
		/**
		 * 附加查询
		 */
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_1())
				&&StringUtils.isNotEmpty(pager.getValueExtra_1())){
			map.put("searchByExtra_1", pager.getSearchByExtra_1());
			map.put("valueExtra_1", pager.getValueExtra_1());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_2())
				&&StringUtils.isNotEmpty(pager.getValueExtra_2())){
			map.put("searchByExtra_2", pager.getSearchByExtra_2());
			map.put("valueExtra_2", pager.getValueExtra_2());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_3())
				&&StringUtils.isNotEmpty(pager.getValueExtra_3())){
			map.put("searchByExtra_3", pager.getSearchByExtra_3());
			map.put("valueExtra_3", pager.getValueExtra_3());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_4())
				&&StringUtils.isNotEmpty(pager.getValueExtra_4())){
			map.put("searchByExtra_4", pager.getSearchByExtra_4());
			map.put("valueExtra_4", pager.getValueExtra_4());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_5())
				&&StringUtils.isNotEmpty(pager.getValueExtra_5())){
			map.put("searchByExtra_5", pager.getSearchByExtra_5());
			map.put("valueExtra_5", pager.getValueExtra_5());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_6())
				&&StringUtils.isNotEmpty(pager.getValueExtra_6())){
			map.put("searchByExtra_6", pager.getSearchByExtra_6());
			map.put("valueExtra_6", pager.getValueExtra_6());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_7())
				&&StringUtils.isNotEmpty(pager.getValueExtra_7())){
			map.put("searchByExtra_7", pager.getSearchByExtra_7());
			map.put("valueExtra_7", pager.getValueExtra_7());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_8())
				&&StringUtils.isNotEmpty(pager.getValueExtra_8())){
			map.put("searchByExtra_8", pager.getSearchByExtra_8());
			map.put("valueExtra_8", pager.getValueExtra_8());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_9())
				&&StringUtils.isNotEmpty(pager.getValueExtra_9())){
			map.put("searchByExtra_9", pager.getSearchByExtra_9());
			map.put("valueExtra_9", pager.getValueExtra_9());
		}
		// 时间查询
		if(StringUtils.isNotEmpty(pager.getTimeBy())){
			map.put("timeBy", pager.getTimeBy());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(pager.getBeginDate()!=null){
				map.put("beginDate", sf.format(pager.getBeginDate()));
			}
			if(pager.getEndDate()!=null){
				map.put("endDate", sf.format(pager.getEndDate()));
			}
		}
		//字段大小比较查询
		if(StringUtils.isNotEmpty(pager.getValueby())){
			map.put("valueby", pager.getValueby());
			if(pager.getValuemin()!=0){
				map.put("valuemin", pager.getValuemin());
			}
			if(pager.getValuemax()!=0){
				map.put("valuemax", pager.getValuemax());
			}
		}
		
		// 查询排序
		if(StringUtils.isNotEmpty(pager.getOrderBy())){
			map.put("orderBy", pager.getOrderBy());
			if(pager.getOrder()!=null){
				map.put("orderDesc", pager.getOrder().name());
			}
		}
		PageInfo<T> pageInfo = findPage(pager.getPageNumber(), pager.getPageSize(), map);
		pager.setResult(pageInfo.getList());
		pager.setTotalCount((int) pageInfo.getTotal());
		return pager;
	}
	
	public Pager findPager(Pager pager,Map<String,Object> map) {
		
		if(map==null){
			map = new HashMap<String,Object>();
		}
		
		// 关键字模糊查询
		if(StringUtils.isNotEmpty(pager.getSearchBy())
				&&StringUtils.isNotEmpty(pager.getKeyword())){
			map.put("searchBy", pager.getSearchBy());
			map.put("keyword", pager.getKeyword());
		}
		/**
		 * 附加查询
		 */
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_1())
				&&StringUtils.isNotEmpty(pager.getValueExtra_1())){
			map.put("searchByExtra_1", pager.getSearchByExtra_1());
			map.put("valueExtra_1", pager.getValueExtra_1());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_2())
				&&StringUtils.isNotEmpty(pager.getValueExtra_2())){
			map.put("searchByExtra_2", pager.getSearchByExtra_2());
			map.put("valueExtra_2", pager.getValueExtra_2());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_3())
				&&StringUtils.isNotEmpty(pager.getValueExtra_3())){
			map.put("searchByExtra_3", pager.getSearchByExtra_3());
			map.put("valueExtra_3", pager.getValueExtra_3());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_4())
				&&StringUtils.isNotEmpty(pager.getValueExtra_4())){
			map.put("searchByExtra_4", pager.getSearchByExtra_4());
			map.put("valueExtra_4", pager.getValueExtra_4());
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_5())
				&&StringUtils.isNotEmpty(pager.getValueExtra_5())){
			map.put("searchByExtra_5", pager.getSearchByExtra_5());
			map.put("valueExtra_5", pager.getValueExtra_5());
		}
		// 时间查询
		if(StringUtils.isNotEmpty(pager.getTimeBy())){
			map.put("timeBy", pager.getTimeBy());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(pager.getBeginDate()!=null){
				map.put("beginDate", sf.format(pager.getBeginDate()));
			}
			if(pager.getEndDate()!=null){
				map.put("endDate", sf.format(pager.getEndDate()));
			}
		}
		// 查询排序
		if(StringUtils.isNotEmpty(pager.getOrderBy())){
			map.put("orderBy", pager.getOrderBy());
			if(pager.getOrder()!=null){
				map.put("orderDesc", pager.getOrder().name());
			}
		}
		PageInfo<T> pageInfo = findPage(pager.getPageNumber(), pager.getPageSize(), map);
		pager.setResult(pageInfo.getList());
		pager.setTotalCount((int) pageInfo.getTotal());
		return pager;
	}
	
}
