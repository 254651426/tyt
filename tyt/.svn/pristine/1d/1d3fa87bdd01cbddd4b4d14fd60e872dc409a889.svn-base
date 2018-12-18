package com.bquan.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.bquan.entity.mysql.Coupon;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserCoupon;

public interface UserCouponWriteService extends BaseWriteService<UserCoupon>{

	/**
	 * 给用户生成红包
	 * @param coupon
	 * @param user
	 * @return
	 */
	public String generateUserCoupon(Coupon coupon,User user);
	
	/**
	 * 使用红包
	 * @param ordersId
	 * @param userCoupon
	 * @return
	 */
	public String useUserCoupon(String ordersId,UserCoupon userCoupon);
	
	/**
	 * 更改订单使用的优惠券的状态
	 * @param orderId
	 * @return
	 */
	public boolean userCouponByOrderId(String orderId);
	
	/**
	 * 获取此价钱可用的红包
	 * @param price
	 * @param user
	 * @return
	 */
	public UserCoupon getAvailableUserCoupon(BigDecimal price ,User user);
	
	/**
	 * 获取这个用户所有可用的红包
	 * @param user
	 * @return
	 */
	public List<UserCoupon> getAvailableList(String userId);
}
