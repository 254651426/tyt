package com.bquan.bean;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
/**
 * web_domain
 */
public class WebDomainBean implements Serializable{
	
	public int id;
	public String domain;
	public String status;
	public String user_id;
	public String type;
	public String is_wall;
	public Date  create_date;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	} 
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getIs_wall() {
		return is_wall;
	}

	public void setIs_wall(String is_wall) {
		this.is_wall = is_wall;
	} 
	
	

	
}
