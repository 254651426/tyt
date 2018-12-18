package com.bquan.service.read;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.bean.UserCouponBean;
import com.bquan.dao.read.UserCouponReadMapper;
import com.bquan.entity.mysql.UserCoupon;
import com.bquan.entity.mysql.UserCoupon.UserCouponStatus;

public class UserCouponReadServiceImpl extends BaseReadServiceImpl<UserCoupon> implements UserCouponReadService {
 
	@Resource(name = "userCouponReadMapper")
	private UserCouponReadMapper userCouponReadMapper;
	
	@Override
	public UserCouponReadMapper getBaseReadMapper() {
		return userCouponReadMapper;
	}
	
	public List<UserCoupon> getData(String userId, UserCouponStatus status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("status", status);
		return userCouponReadMapper.select(map);
	}

	public List<UserCouponBean> convertDate(List<UserCoupon> userCouponList) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<UserCouponBean> resultList = new ArrayList<UserCouponBean>();
		for(UserCoupon userCoupon:userCouponList){
			try {
				String img = "";
				if(userCoupon.getCoupon()!=null){
					img = userCoupon.getCoupon().getImgUrl();
				}
				resultList.add(
						new UserCouponBean(userCoupon.getId(), 
								userCoupon.getPrice().toString(), 
								userCoupon.getMinimum().toString(), 
								sf.format(userCoupon.getCreateDate()), 
								sf.format(userCoupon.getLimitDate()),
								img));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

}