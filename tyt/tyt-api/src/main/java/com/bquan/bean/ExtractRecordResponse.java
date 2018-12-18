package com.bquan.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.bquan.bean.CommonConstant.Status;
import com.bquan.entity.mysql.Commission;

public class ExtractRecordResponse implements Serializable {

	private Status status;
	private String msg = "";
	//private List<ExtractCommission> extractCommissionList;
	private List<Commission> commissionList;
	private Integer allUser;
	private Integer vipUser;
	private BigDecimal commissionMoney;
	
	public ExtractRecordResponse(){}
	
	public ExtractRecordResponse(
			Status status,
			String msg,
			//List<ExtractCommission> extractCommissionList,
			List<Commission> commissionList,
			Integer allUser,
			Integer vipUser,
			BigDecimal commissionMoney){
		this.status=status;
		this.msg=msg;
		//this.extractCommissionList=extractCommissionList;
		this.commissionList=commissionList;
		this.allUser=allUser;
		this.vipUser=vipUser;
		this.commissionMoney=commissionMoney;
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

//	public List<ExtractCommission> getExtractCommissionList() {
//		return extractCommissionList;
//	}
//
//	public void setExtractCommissionList(List<ExtractCommission> extractCommissionList) {
//		this.extractCommissionList = extractCommissionList;
//	}

	public List<Commission> getCommissionList() {
		return commissionList;
	}

	public void setCommissionList(List<Commission> commissionList) {
		this.commissionList = commissionList;
	}

	public Integer getAllUser() {
		return allUser;
	}

	public void setAllUser(Integer allUser) {
		this.allUser = allUser;
	}

	public Integer getVipUser() {
		return vipUser;
	}

	public void setVipUser(Integer vipUser) {
		this.vipUser = vipUser;
	}

	public BigDecimal getCommissionMoney() {
		return commissionMoney;
	}

	public void setCommissionMoney(BigDecimal commissionMoney) {
		this.commissionMoney = commissionMoney;
	}
	
}
