package com.bquan.entity.mysql;

import java.io.Serializable;

/**
 * 提示语 Entity
 * @author liuxiaokang
 * @createTime 2016-08-26
 */
public class Tips extends BaseStrEntity{

	private String content;//
	private Integer isExpired;//
	private String url;//
 
	public String getContent() {  
        return content;  
    }  
    public void setContent(String content) {  
        this.content = content;  
    }
	public Integer getIsExpired() {  
        return isExpired;  
    }  
    public void setIsExpired(Integer isExpired) {  
        this.isExpired = isExpired;  
    }
	public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }
}
