package com.bquan.bean;

import java.io.Serializable;
import java.util.List;


/**
 * ajax返回json对象实体类
 * 
 * @author elliot
 * 
 */
public class AjaxResponse  implements Serializable{

	/**
	 * 成功
	 */
	public static final Integer SUCCESS = 0;

	/**
	 * 失败
	 */
	public static final Integer FAILURE = -1;

	/**
	 * 登录超时
	 */
	public static final Integer LOGINTIMEOUT = 1;

	/**
	 * 返回码
	 */
	private Integer code = SUCCESS;

	/**
	 * 返回消息
	 */
	private String msg;

	/**
	 * 数据记录对象
	 */
	private Object record;

	/**
	 * 数据记录列表
	 */
	private List<?> records;

	/**
	 * 分页
	 */
	private Pagination page;
	
	private Integer userid;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRecord() {
		return record;
	}

	public void setRecord(Object record) {
		this.record = record;
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}