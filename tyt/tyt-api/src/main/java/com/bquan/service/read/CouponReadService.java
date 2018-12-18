package com.bquan.service.read;

import java.util.List;

import com.bquan.entity.mysql.Coupon;

public interface CouponReadService extends BaseReadService<Coupon>{

	/**
	 * 通过标识获取优惠券
	 * @param sign
	 * @return
	 */
	public Coupon getDataBySign(String sign);

}
