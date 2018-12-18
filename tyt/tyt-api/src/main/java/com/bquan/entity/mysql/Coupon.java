package com.bquan.entity.mysql;

import java.math.BigDecimal;
/**
 *	红包
 * @author Administrator
 *
 */
public class Coupon extends BaseIntEntity{

	private static final long serialVersionUID = 2254777154104302874L;

	private String sign;			// 红包编码
	private String name;			// 红包名称
	private BigDecimal price;		// 红包价钱
	private BigDecimal minimum;		// 使用此红包的最低消费
	private Integer day;			// 用户拿到此红包的有效期为当前时间加上day天
	private Boolean isExpired;		// 是否过期
	private String imgUrl;			// 红包图片
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
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
	public BigDecimal getMinimum() {
		return minimum;
	}
	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}
	public Boolean getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
