package com.bquan.entity.mysql;

import java.math.BigDecimal;
import java.util.Date;

/**
 *	用户协议
 * @author Administrator
 *
 */
public class User extends BaseStrEntity{

	private static final long serialVersionUID = 2254777154104302874L;

	private String username;			// 账号
	private Date buyDate;
	private Integer timeLeft;
	private String phone;
	private Boolean status;
	private Boolean isBlack;
	private Boolean userSource;
	private String password;			// 密码两次md5加密
	private String level;				// 用户等级  0免费   1保留未用   2包月，3包季度  4包年
	private String token;
	private String ip;
	private String fmcode;
	private Date loginDate;
	private String version;				// 最后一次登录版本号
	private Date vipEndTime;			// vip截至时间
	private Date phoneEndTime;			// 手机端vip剩余时间
	private String shareCode;
	private Integer leftCount;
	private Boolean isVipSend;			// 标记佣金是否已赠送（true代表已赠送）
	private Boolean isRegisterSend;		// 目前此字段未使用
	private Date freeStartTime;
	private Date signTime;
	private Boolean isEmailSend;		// 此字段为false时，登陆会送10分钟，然后将状态改为true
	private Date sendDate;				// 赠送免费时长的时间
	private BigDecimal commissionAmount;// 佣金数量
	private Integer limitSendTime;		// 最高赠送时间
	private String shadowsocksPassword;
	private String shadowsocksPort;
	private String depname;            //机构
	private String daynum;
	private String note;
	private String type;  //手机类型（0 安卓 1 ios）
	private String phonenum;
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDaynum() {
		return daynum;
	}
	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Integer getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(Integer timeLeft) {
		this.timeLeft = timeLeft;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Boolean getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(Boolean isBlack) {
		this.isBlack = isBlack;
	}
	public Boolean getUserSource() {
		return userSource;
	}
	public void setUserSource(Boolean userSource) {
		this.userSource = userSource;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFmcode() {
		return fmcode;
	}
	public void setFmcode(String fmcode) {
		this.fmcode = fmcode;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getVipEndTime() {
		return vipEndTime;
	}
	public void setVipEndTime(Date vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	public String getShareCode() {
		return shareCode;
	}
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	public Integer getLeftCount() {
		return leftCount;
	}
	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}
	public Boolean getIsVipSend() {
		return isVipSend;
	}
	public void setIsVipSend(Boolean isVipSend) {
		this.isVipSend = isVipSend;
	}
	public Boolean getIsRegisterSend() {
		return isRegisterSend;
	}
	public void setIsRegisterSend(Boolean isRegisterSend) {
		this.isRegisterSend = isRegisterSend;
	}
	public Date getFreeStartTime() {
		return freeStartTime;
	}
	public void setFreeStartTime(Date freeStartTime) {
		this.freeStartTime = freeStartTime;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public Boolean getIsEmailSend() {
		return isEmailSend;
	}
	public void setIsEmailSend(Boolean isEmailSend) {
		this.isEmailSend = isEmailSend;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getLimitSendTime() {
		return limitSendTime;
	}
	public void setLimitSendTime(Integer limitSendTime) {
		this.limitSendTime = limitSendTime;
	}
	public Date getPhoneEndTime() {
		return phoneEndTime;
	}
	public void setPhoneEndTime(Date phoneEndTime) {
		this.phoneEndTime = phoneEndTime;
	}
	public String getShadowsocksPassword() {
		return shadowsocksPassword;
	}
	public void setShadowsocksPassword(String shadowsocksPassword) {
		this.shadowsocksPassword = shadowsocksPassword;
	}
	public String getShadowsocksPort() {
		return shadowsocksPort;
	}
	public void setShadowsocksPort(String shadowsocksPort) {
		this.shadowsocksPort = shadowsocksPort;
	}
	
}
