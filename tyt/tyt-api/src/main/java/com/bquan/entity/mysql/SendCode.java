package com.bquan.entity.mysql;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮箱验证码 Entity
 * @author liuxiaokang
 * @createTime 2016-08-27
 */
public class SendCode extends BaseIntEntity{

	private String code;//
	private String username;//
	private Date sendTime;//
	private Integer isExpired;//
 
	public String getCode() {  
        return code;  
    }  
    public void setCode(String code) {  
        this.code = code;  
    }
	public String getUsername() {  
        return username;  
    }  
    public void setUsername(String username) {  
        this.username = username;  
    }
	public Date getSendTime() {  
        return sendTime;  
    }  
    public void setSendTime(Date sendTime) {  
        this.sendTime = sendTime;  
    }
	public Integer getIsExpired() {  
        return isExpired;  
    }  
    public void setIsExpired(Integer isExpired) {  
        this.isExpired = isExpired;  
    }
}
