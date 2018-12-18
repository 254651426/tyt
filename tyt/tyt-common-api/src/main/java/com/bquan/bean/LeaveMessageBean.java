package com.bquan.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户留言model
 */
public class LeaveMessageBean implements Serializable{
	public Integer id;
	public String info;
	public Date createTime;
	public String user_id;
	public String status; //0:未回复 1：已回复
	public Integer back_user_id;
	public String username;
	public String adminname;
	public String back_info;
	public Date back_time;//回复时间
	
	
	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBack_user_id() {
		return back_user_id;
	}

	public void setBack_user_id(Integer back_user_id) {
		this.back_user_id = back_user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBack_info() {
		return back_info;
	}

	public void setBack_info(String back_info) {
		this.back_info = back_info;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	
}
