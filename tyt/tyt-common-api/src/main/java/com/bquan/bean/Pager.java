package com.bquan.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bquan.util.SQLFilterUtil;

/**
 * Bean类 - 分页
 */

public class Pager implements Serializable{

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制
	
	// 排序方式（递增、递减）
	public enum Order {
		asc, desc
	}

	private int pageNumber = 1;// 当前页码
	private int pageSize = 20;// 每页记录数
	private String searchBy;// 查找字段
	private String keyword;// 查找关键字
	private String orderBy;// 排序字段
	private Date beginDate;// 开始时间
	private Date endDate;// 结束时间
	private String timeBy;// 查找时间字段
	private Order order;// 排序方式
	private String valueby;// 查询字段
	private int valuemax;// 最大字段值
	private int valuemin;// 最小字段值

	private String searchByExtra_1 ;// 备用查询字段名1
	private String searchByExtra_2 ;// 备用查询字段名2
	private String searchByExtra_3 ;// 备用查询字段名3
	private String searchByExtra_4 ;// 备用查询字段名4
	private String searchByExtra_5 ;// 备用查询字段名5
	private String searchByExtra_6 ;// 备用查询字段名6
	private String searchByExtra_7 ;// 备用查询字段名7
	private String searchByExtra_8 ;// 备用查询字段名8
	private String searchByExtra_9 ;// 备用查询字段名9
	private String valueExtra_1; // 备用查询字段值1
	private String valueExtra_2; // 备用查询字段值2
	private String valueExtra_3; // 备用查询字段值3
	private String valueExtra_4; // 备用查询字段值4
	private String valueExtra_5; // 备用查询字段值5
	private String valueExtra_6; // 备用查询字段值6
	private String valueExtra_7; // 备用查询字段值7
	private String valueExtra_8; // 备用查询字段值8
	private String valueExtra_9; // 备用查询字段值9
	private int totalCount;// 总记录数
	private List<?> result;// 返回结果
	
	public Pager(){}
	
	public Pager(Map<String,Object> map) throws Exception{
		
		// 防sql注入
		for(String key:map.keySet()){
			if(StringUtils.isNotEmpty(key)){
				if(SQLFilterUtil.sqlInject(key)){
					throw new Exception("sql注入异常");
				}
				if(map.get(key)!=null){
					if(SQLFilterUtil.sqlInject(map.get(key).toString())){
						throw new Exception("sql注入异常");
					}
				}
			}
		}
		
		if(map.get("pageNumber")!=null){
			this.pageNumber=Integer.parseInt(map.get("pageNumber").toString());
		}
		
		if(map.get("pageSize")!=null){
			this.pageSize=Integer.parseInt(map.get("pageSize").toString());
		}
		
		if(map.get("orderBy")!=null){
			this.orderBy=com.bquan.util.StringUtils.camelToUnderline(map.get("orderBy").toString());
		}
		
		if(map.get("order")!=null){
			this.order=Order.valueOf(map.get("order").toString());
		}
		
		if(map.get("searchBy")!=null){
			this.searchBy=map.get("searchBy").toString();
		}
		
		if(map.get("keyword")!=null){
			this.keyword=map.get("keyword").toString();
		}
	}
	
	// 获取总页数
	public int getPageCount() {
		int pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getTimeBy() {
		return timeBy;
	}

	public void setTimeBy(String timeBy) {
		this.timeBy = timeBy;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

	public String getSearchByExtra_1() {
		return searchByExtra_1;
	}

	public void setSearchByExtra_1(String searchByExtra_1) {
		this.searchByExtra_1 = searchByExtra_1;
	}

	public String getSearchByExtra_2() {
		return searchByExtra_2;
	}

	public void setSearchByExtra_2(String searchByExtra_2) {
		this.searchByExtra_2 = searchByExtra_2;
	}

	public String getSearchByExtra_3() {
		return searchByExtra_3;
	}

	public void setSearchByExtra_3(String searchByExtra_3) {
		this.searchByExtra_3 = searchByExtra_3;
	}

	public String getSearchByExtra_4() {
		return searchByExtra_4;
	}

	public void setSearchByExtra_4(String searchByExtra_4) {
		this.searchByExtra_4 = searchByExtra_4;
	}

	public String getSearchByExtra_5() {
		return searchByExtra_5;
	}

	public void setSearchByExtra_5(String searchByExtra_5) {
		this.searchByExtra_5 = searchByExtra_5;
	}

	public String getSearchByExtra_6() {
		return searchByExtra_6;
	}

	public void setSearchByExtra_6(String searchByExtra_6) {
		this.searchByExtra_6 = searchByExtra_6;
	}

	public String getSearchByExtra_7() {
		return searchByExtra_7;
	}

	public void setSearchByExtra_7(String searchByExtra_7) {
		this.searchByExtra_7 = searchByExtra_7;
	}

	public String getSearchByExtra_8() {
		return searchByExtra_8;
	}

	public void setSearchByExtra_8(String searchByExtra_8) {
		this.searchByExtra_8 = searchByExtra_8;
	}

	public String getSearchByExtra_9() {
		return searchByExtra_9;
	}

	public void setSearchByExtra_9(String searchByExtra_9) {
		this.searchByExtra_9 = searchByExtra_9;
	}

	public String getValueExtra_1() {
		return valueExtra_1;
	}

	public void setValueExtra_1(String valueExtra_1) {
		this.valueExtra_1 = valueExtra_1;
	}

	public String getValueExtra_2() {
		return valueExtra_2;
	}

	public void setValueExtra_2(String valueExtra_2) {
		this.valueExtra_2 = valueExtra_2;
	}

	public String getValueExtra_3() {
		return valueExtra_3;
	}

	public void setValueExtra_3(String valueExtra_3) {
		this.valueExtra_3 = valueExtra_3;
	}

	public String getValueExtra_4() {
		return valueExtra_4;
	}

	public void setValueExtra_4(String valueExtra_4) {
		this.valueExtra_4 = valueExtra_4;
	}

	public String getValueExtra_5() {
		return valueExtra_5;
	}

	public void setValueExtra_5(String valueExtra_5) {
		this.valueExtra_5 = valueExtra_5;
	}

	public String getValueExtra_6() {
		return valueExtra_6;
	}

	public void setValueExtra_6(String valueExtra_6) {
		this.valueExtra_6 = valueExtra_6;
	}

	public String getValueExtra_7() {
		return valueExtra_7;
	}

	public void setValueExtra_7(String valueExtra_7) {
		this.valueExtra_7 = valueExtra_7;
	}

	public String getValueExtra_8() {
		return valueExtra_8;
	}

	public void setValueExtra_8(String valueExtra_8) {
		this.valueExtra_8 = valueExtra_8;
	}

	public String getValueExtra_9() {
		return valueExtra_9;
	}

	public void setValueExtra_9(String valueExtra_9) {
		this.valueExtra_9 = valueExtra_9;
	}

	public String getValueby() {
		return valueby;
	}

	public void setValueby(String valueby) {
		this.valueby = valueby;
	}

	public int getValuemax() {
		return valuemax;
	}

	public void setValuemax(int valuemax) {
		this.valuemax = valuemax;
	}

	public int getValuemin() {
		return valuemin;
	}

	public void setValuemin(int valuemin) {
		this.valuemin = valuemin;
	}

	
}