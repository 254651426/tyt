package com.bquan.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分页对象，用于容纳分页查询所得相关信息，如数据列表，页码等
 * 
 * @author elliot
 * 
 */
public class Pagination implements Serializable {

	private static final long serialVersionUID = 8719855803676768829L;
	/**
	 * 当前页码
	 */
	private int pageNo = 1;
	/**
	 * 总页数
	 */
	private int pageSize = 12;
	/**
	 * 查询结果集
	 */
	private List<?> results;
	/**
	 * 总记录数
	 */
	private int totalRow;
	/**
	 * 总页数
	 */
	private int totalPage;
	
	private Map<String,Object> paramterMap;
	
	private String searchBy;// 查找字段
	private String keyword;// 查找关键字
	private String orderBy;// 排序字段
	private Date beginDate;// 开始时间
	private Date endDate;// 结束时间
	private String timeBy;// 查找时间字段
	
	private String searchByExtra_1 ;// 备用查询字段名1
	private String searchByExtra_2 ;// 备用查询字段名2
	private String searchByExtra_3 ;// 备用查询字段名3
	private String searchByExtra_4 ;// 备用查询字段名4
	private String searchByExtra_5 ;// 备用查询字段名5
	private String valueExtra_1; // 备用查询字段值1
	private String valueExtra_2; // 备用查询字段值2
	private String valueExtra_3; // 备用查询字段值3
	private String valueExtra_4; // 备用查询字段值4
	private String valueExtra_5; // 备用查询字段值5

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalPage() {
		int s = this.totalRow / this.pageSize;
		int m = this.totalRow % this.pageSize;
		if (m > 0) {
			s += 1;
		}
		this.totalPage = (s == 0 ? 1 : s);
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Map<String,Object> getParamterMap() {
		return paramterMap;
	}

	public void setParamterMap(Map<String,Object> paramterMap) {
		this.paramterMap = paramterMap;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTimeBy() {
		return timeBy;
	}

	public void setTimeBy(String timeBy) {
		this.timeBy = timeBy;
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
}
