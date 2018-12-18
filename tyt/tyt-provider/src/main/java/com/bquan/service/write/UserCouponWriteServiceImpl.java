package com.bquan.service.write;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.bquan.dao.read.UserCouponReadMapper;
import com.bquan.dao.write.UserCouponWriteMapper;
import com.bquan.entity.mysql.Coupon;
import com.bquan.entity.mysql.Orders;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserCoupon;
import com.bquan.entity.mysql.UserCoupon.UserCouponStatus;
import com.bquan.service.read.OrdersReadService;
import com.bquan.service.read.UserCouponReadService;
import com.bquan.util.BigDecimalCalUtil;
import com.bquan.util.SpringUtil;


public class UserCouponWriteServiceImpl extends BaseWriteServiceImpl<UserCoupon> implements UserCouponWriteService {
 
	@Resource
	private UserCouponWriteMapper userCouponWriteMapper;
	@Resource
	private UserCouponReadMapper userCouponReadMapper;
	
	private OrdersReadService ordersReadService= 
			(OrdersReadService) SpringUtil.getBean("ordersReadService");
	private UserCouponReadService userCouponReadService= 
			(UserCouponReadService) SpringUtil.getBean("userCouponReadService");
	
	@Override
	public UserCouponWriteMapper getBaseWriteMapper() {
		return userCouponWriteMapper;
	}

	public String generateUserCoupon(Coupon coupon, User user) {
		if(coupon==null){
			return "优惠券不能为空";
		}
		if(user==null){
			return "会员不能为空";
		}
		try {
			Calendar now = Calendar.getInstance();
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setCouponId(String.valueOf(coupon.getId()));
			now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH)+coupon.getDay());
			userCoupon.setLimitDate(now.getTime());
			userCoupon.setCreateDate(new Date());
			userCoupon.setModifyDate(new Date());
			userCoupon.setOrdersId(null);
			userCoupon.setPrice(coupon.getPrice());
			userCoupon.setStatus(UserCouponStatus.unuse);
			userCoupon.setUserId(user.getId());
			userCoupon.setMinimum(coupon.getMinimum());
			userCoupon.setMinimum(coupon.getMinimum());
			insert(userCoupon);
		} catch (Exception e) {
			e.printStackTrace();
			return "优惠券生成失败";
		}
		return "success";
	}

	public String useUserCoupon(String ordersId, UserCoupon userCoupon) {
		userCoupon.setStatus(UserCouponStatus.used);
		userCoupon.setOrdersId(ordersId);
		userCoupon.setUseDate(new Date());
		update(userCoupon);
		return "success";
	}
	
	public UserCoupon getAvailableUserCoupon(BigDecimal price, User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		// 查询红包，按面额的从大到小排序
		map.put("userId", user.getId());
		map.put("status", UserCouponStatus.unuse);
		List<UserCoupon> list = userCouponReadMapper.select(map);
		// 查询出一个可用的红包
		for(UserCoupon userCoupon:list){
			if(BigDecimalCalUtil.compare(price, userCoupon.getMinimum())!=-1
					&&userCoupon.getLimitDate().after(new Date())){
				return userCoupon;
			}
		}
		return null;
	}

	public List<UserCoupon> getAvailableList(String userId) {
		if(StringUtils.isEmpty(userId)){
			return new ArrayList<UserCoupon>();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("status", UserCouponStatus.unuse);
		List<UserCoupon> list = userCouponReadMapper.select(map);
		Iterator<UserCoupon> it = list.iterator();
		while(it.hasNext()){
			UserCoupon uc = it.next();
			// 检测红包是否已过期，过期则修改红包状态并移除
			if(uc.getLimitDate().before(new Date())){
				uc.setStatus(UserCouponStatus.expired);
				update(uc);
				it.remove();
			}
		}
		return list;
	}

	public boolean userCouponByOrderId(String orderId) {
		if(StringUtils.isEmpty(orderId)){
			return false;
		}
		Orders orders = ordersReadService.get(orderId);
		if(orders!=null){
			String userCouponId = orders.getUserCouponId();
			if(StringUtils.isNotEmpty(userCouponId)){
				UserCoupon uc = userCouponReadService.get(userCouponId);
				if(uc!=null){
					uc.setStatus(UserCouponStatus.used);
					uc.setOrdersId(orderId);
					uc.setUseDate(new Date());
					update(uc);
					return true;
				}
			}
		}
		return false;
	}

}