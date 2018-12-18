package com.bquan.entity.mongo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * 适用于id为String类型
 * @author lxk
 *
 */
public class BaseMongodbEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009757825146095484L;
	
	@Id
	private String id=UUID.randomUUID().toString();					// 类别的id
	private Date createDate = new Date();										// 创建时间
	private Date modifyDate = new Date();										// 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
