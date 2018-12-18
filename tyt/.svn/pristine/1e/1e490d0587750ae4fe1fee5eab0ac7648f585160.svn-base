package com.bquan.entity.mysql;

import java.math.BigDecimal;
import java.util.Date;

/**
 *	用户红包
 * @author Administrator
 *
 */
public class UserCoupon extends BaseStrEntity{

	private static final long serialVersionUID = 2254777154104302874L;

	// 优惠券状态
	public enum UserCouponStatus{
		// 未使用，已使用，已过期
		unuse,used,expired
	}
	
	private User user;						// 关联用户
	private Coupon coupon;					// 关联红包
	private BigDecimal price;				// 红包价钱
	private Date limitDate;					// 用户红包截至日期
	private UserCouponStatus status;		// 优惠券是否过期
	private Date useDate;					// 红包使用时间
	private String ordersId;				// 红包使用的订单的id
	private BigDecimal minimum;				// 使用此红包的最低消费
	
	private String userId;
	private String couponId;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public UserCouponStatus getStatus() {
		return status;
	}
	public void setStatus(UserCouponStatus status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public BigDecimal getMinimum() {
		return minimum;
	}
	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}
	
}
