package com.bquan.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.bquan.entity.mysql.User;

public interface UserWriteService extends BaseWriteService<User>{

	/**
	 * 赠送佣金
	 * @param username			购买vip的用户名
	 * @param commission		购买产品价格
	 */
	public boolean setCommission(String orderId,String username,BigDecimal commission);
	
	/**
	 * 生成shadowsocks账号
	 */
	public void generateShadowsocks();
	
}
