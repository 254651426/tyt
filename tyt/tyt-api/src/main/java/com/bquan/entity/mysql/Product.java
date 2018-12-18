package com.bquan.entity.mysql;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品 Entity
 * @author liuxiaokang
 * @createTime 2016-08-26
 */
public class Product extends BaseIntEntity{

	private String name;//产品名称
	private BigDecimal price;//单价
	private String content;//产品描述
	private String sign;//产品标识
	private Integer isExpired;//是否启用  0代表停用，1代表启用
	private Integer day;//vip总天数
	private Integer type;//
	private String html;//
 
	public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public BigDecimal getPrice() {  
        return price;  
    }  
    public void setPrice(BigDecimal price) {  
        this.price = price;  
    }
	public String getContent() {  
        return content;  
    }  
    public void setContent(String content) {  
        this.content = content;  
    }
	public String getSign() {  
        return sign;  
    }  
    public void setSign(String sign) {  
        this.sign = sign;  
    }
	public Integer getIsExpired() {  
        return isExpired;  
    }  
    public void setIsExpired(Integer isExpired) {  
        this.isExpired = isExpired;  
    }
	public Integer getDay() {  
        return day;  
    }  
    public void setDay(Integer day) {  
        this.day = day;  
    }
	public Integer getType() {  
        return type;  
    }  
    public void setType(Integer type) {  
        this.type = type;  
    }
	public String getHtml() {  
        return html;  
    }  
    public void setHtml(String html) {  
        this.html = html;  
    }
}
