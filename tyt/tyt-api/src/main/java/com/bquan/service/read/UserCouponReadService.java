package com.bquan.service.read;

import java.math.BigDecimal;
import java.util.List;

import com.bquan.bean.UserCouponBean;
import com.bquan.entity.mysql.UserCoupon;
import com.bquan.entity.mysql.UserCoupon.UserCouponStatus;

public interface UserCouponReadService extends BaseReadService<UserCoupon>{

	/**
	 * 获取会员优惠券
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserCoupon> getData(String userId,UserCouponStatus status);
	
	/**
	 * 转换成客户端需要的数据
	 * @param userCouponList
	 * @return
	 */
	public List<UserCouponBean> convertDate(List<UserCoupon> userCouponList);
	
}
