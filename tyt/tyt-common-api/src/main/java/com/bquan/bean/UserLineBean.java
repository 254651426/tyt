package com.bquan.bean;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
/**
 * user_line
 */
public class UserLineBean implements Serializable{
	public int id;
	public String user_ip;
	public String area;
	public String is_del;
	public java.util.Date create_date;
	public String port;
	public String http;
	public int level;
	public int ip_count;
	public int def_count;
	public int rel_count;
	public int com_count;
	public int com_count_two;
	public String status ;
	public String  description;
	public String line_ip;
	public String html;
	// 是否免费线路（0代表是免费线路，1代表是付费线路）
	public Integer is_free;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public UserLineBean() {
		super();
	}
	
	public UserLineBean(
			int id, String user_ip, 
			String area, String is_del, 
			java.util.Date create_date, 
			String port, String http, 
			int level, int def_count, 
			int rel_count, int com_count, 
			int com_count_two,String line_ip,
			String status,String description) {
		super();
		this.id = id; 
		this.user_ip = user_ip; 
		this.area = area; 
		this.is_del = is_del; 
		this.create_date = create_date; 
		this.port = port; 
		this.http = http; 
		this.level = level; 
		this.def_count = def_count; 
		this.rel_count = rel_count; 
		this.com_count = com_count; 
		this.com_count_two = com_count_two; 
		this.line_ip=line_ip;
		this.status=status;
		this.description=description;
	}
	
	public String getLine_ip() {
		return line_ip;
	}

	public void setLine_ip(String line_ip) {
		this.line_ip = line_ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 
	
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	} 
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	} 
	
	public String getIs_del() {
		return is_del;
	}

	public void setIs_del(String is_del) {
		this.is_del = is_del;
	} 
	
	public java.util.Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(java.util.Date create_date) {
		this.create_date = create_date;
	} 
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	} 
	
	public String getHttp() {
		return http;
	}

	public void setHttp(String http) {
		this.http = http;
	} 
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	} 
	
	public int getDef_count() {
		return def_count;
	}

	public void setDef_count(int def_count) {
		this.def_count = def_count;
	} 
	
	public int getRel_count() {
		return rel_count;
	}

	public void setRel_count(int rel_count) {
		this.rel_count = rel_count;
	} 
	
	public int getCom_count() {
		return com_count;
	}

	public void setCom_count(int com_count) {
		this.com_count = com_count;
	} 
	
	public int getCom_count_two() {
		return com_count_two;
	}

	public void setCom_count_two(int com_count_two) {
		this.com_count_two = com_count_two;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIs_free() {
		return is_free;
	}

	public void setIs_free(Integer is_free) {
		this.is_free = is_free;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public int getIp_count() {
		return ip_count;
	}

	public void setIp_count(int ip_count) {
		this.ip_count = ip_count;
	} 
	
}
