package com.bquan.entity.mysql;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
/**
 * 订单 Entity
 * @author liuxiaokang
 */
public class Orders extends BaseStrEntity{

	private String productName;//
	private BigDecimal productPrice;//
	private String productSign;//
	private String orderId;//
	private String userId;//
	private Date payCreateDate;//
	private Date payDate;//
	private String payDateStr;//
	private Integer orderStatus;//
	private String prePayOrderId;//
	private String weixinOrderId;//
	private String aliOrderId;//
	private String userCouponId;//
	private Integer count;//
	private Boolean isCallBack;//是否回调了
	private String type;
	public String getPayDateStr() {
		return payDateStr;
	}
	public void setPayDateStr(String payDateStr) {
		this.payDateStr = payDateStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private User user;
 
	public String getProductName() {  
        return productName;  
    }  
    public void setProductName(String productName) {  
        this.productName = productName;  
    }
	public BigDecimal getProductPrice() {  
        return productPrice;  
    }  
    public void setProductPrice(BigDecimal productPrice) {  
        this.productPrice = productPrice;  
    }
	public String getProductSign() {  
        return productSign;  
    }  
    public void setProductSign(String productSign) {  
        this.productSign = productSign;  
    }
	public String getOrderId() {  
        return orderId;  
    }  
    public void setOrderId(String orderId) {  
        this.orderId = orderId;  
    }
	public String getUserId() {  
        return userId;  
    }  
    public void setUserId(String userId) {  
        this.userId = userId;  
    }
	public Date getPayCreateDate() {  
        return payCreateDate;  
    }  
    public void setPayCreateDate(Date payCreateDate) {  
        this.payCreateDate = payCreateDate;  
    }
	public Date getPayDate() {  
        return payDate;  
    }  
    public void setPayDate(Date payDate) {  
        this.payDate = payDate;  
    }
	public Integer getOrderStatus() {  
        return orderStatus;  
    }  
    public void setOrderStatus(Integer orderStatus) {  
        this.orderStatus = orderStatus;  
    }
	public String getPrePayOrderId() {  
        return prePayOrderId;  
    }  
    public void setPrePayOrderId(String prePayOrderId) {  
        this.prePayOrderId = prePayOrderId;  
    }
	public String getWeixinOrderId() {  
        return weixinOrderId;  
    }  
    public void setWeixinOrderId(String weixinOrderId) {  
        this.weixinOrderId = weixinOrderId;  
    }
	public String getAliOrderId() {  
        return aliOrderId;  
    }  
    public void setAliOrderId(String aliOrderId) {  
        this.aliOrderId = aliOrderId;  
    }
	public String getUserCouponId() {  
        return userCouponId;  
    }  
    public void setUserCouponId(String userCouponId) {  
        this.userCouponId = userCouponId;  
    }
	public Integer getCount() {  
        return count;  
    }  
    public void setCount(Integer count) {  
        this.count = count;  
    }
	public Boolean getIsCallBack() {  
        return isCallBack;  
    }  
    public void setIsCallBack(Boolean isCallBack) {  
        this.isCallBack = isCallBack;  
    }
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
    
}
