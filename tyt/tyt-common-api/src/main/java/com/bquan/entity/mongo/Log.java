package com.bquan.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 实体类 - 日志
 */

@Document
public class Log extends BaseMongodbEntity {

	private static final long serialVersionUID = -4494144902110236826L;

	private String operation;// 操作名称
	private String operator;// 操作员
	private String actionClass;// Action名称
	private String actionMethod;// Action方法名称
	private String accesssSource; // 来源
	private String logType;// 日志类别
	private String logDesc;// 日志类别名称
	private String ip;// IP
	private String info;// 日志信息
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	public String getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}
	public String getAccesssSource() {
		return accesssSource;
	}
	public void setAccesssSource(String accesssSource) {
		this.accesssSource = accesssSource;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}