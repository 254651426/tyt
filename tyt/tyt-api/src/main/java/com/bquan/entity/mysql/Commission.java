package com.bquan.entity.mysql;

import java.math.BigDecimal;

/**
 *	用户协议
 * @author Administrator
 *
 */
public class Commission extends BaseStrEntity{

	private static final long serialVersionUID = 2254777154104302874L;

	private String username ;			// 购买vip服务的人
	private String inviteUsername;		// 获赠人(提现人)
	private String orderId;
	private BigDecimal price;
	private BigDecimal sendMoney;
	private Boolean isSend;				// 赠送还是体现  true代表赠送   false代表体现操作
	
	private Orders orders;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInviteUsername() {
		return inviteUsername;
	}
	public void setInviteUsername(String inviteUsername) {
		this.inviteUsername = inviteUsername;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSendMoney() {
		return sendMoney;
	}
	public void setSendMoney(BigDecimal sendMoney) {
		this.sendMoney = sendMoney;
	}
	public Boolean getIsSend() {
		return isSend;
	}
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}
	/**
	 * @return the orders
	 */
	public Orders getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
}
