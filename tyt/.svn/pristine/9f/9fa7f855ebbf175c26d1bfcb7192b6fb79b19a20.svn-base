package com.bquan.bean;

import java.io.Serializable;
import java.util.List;

import com.bquan.bean.CommonConstant.Status;

public class PhoneLeaveMessageResponse implements Serializable {

	private Status status;
	private String msg = "";
	private List<LeaveMessageBean> leaveMessageBeanList;
	
	public PhoneLeaveMessageResponse(){}
	
	public PhoneLeaveMessageResponse(
			Status status,
			String msg){
		this.status=status;
		this.msg=msg;
	}
	
	public PhoneLeaveMessageResponse(
			Status status,
			String msg,
			List<LeaveMessageBean> leaveMessageBeanList){
		this.status=status;
		this.msg=msg;
		this.leaveMessageBeanList=leaveMessageBeanList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<LeaveMessageBean> getLeaveMessageBeanList() {
		return leaveMessageBeanList;
	}

	public void setLeaveMessageBeanList(List<LeaveMessageBean> leaveMessageBeanList) {
		this.leaveMessageBeanList = leaveMessageBeanList;
	}
	
	

}
