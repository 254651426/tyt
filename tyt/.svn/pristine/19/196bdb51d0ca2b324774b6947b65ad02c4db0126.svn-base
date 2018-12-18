package com.bquan.entity.mysql;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言 Entity
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public class LeaveMessage extends BaseStrEntity{

	private String info;//留言内容
	private Date createTime;//
	private String userId;//发布信息用户id
	private Boolean status;//0没有回复 1 已经回复
	private String backUserId;//回复信息用户id
	private String backInfo;//
	private Date backTime;//
	
	private User user;// 外键关联user对象
	private Admin admin;// 外键关联admin对象
 
	public String getInfo() {  
        return info;  
    }  
    public void setInfo(String info) {  
        this.info = info;  
    }
	public Date getCreateTime() {  
        return createTime;  
    }  
    public void setCreateTime(Date createTime) {  
        this.createTime = createTime;  
    }
	public String getUserId() {  
        return userId;  
    }  
    public void setUserId(String userId) {  
        this.userId = userId;  
    }
	public Boolean getStatus() {  
        return status;  
    }  
    public void setStatus(Boolean status) {  
        this.status = status;  
    }
	public String getBackUserId() {  
        return backUserId;  
    }  
    public void setBackUserId(String backUserId) {  
        this.backUserId = backUserId;  
    }
	public String getBackInfo() {  
        return backInfo;  
    }  
    public void setBackInfo(String backInfo) {  
        this.backInfo = backInfo;  
    }
	public Date getBackTime() {  
        return backTime;  
    }  
    public void setBackTime(Date backTime) {  
        this.backTime = backTime;  
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
