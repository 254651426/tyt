package com.bquan.service.read;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.dao.read.CouponReadMapper;
import com.bquan.entity.mysql.Coupon;
import com.github.pagehelper.PageInfo;

public class CouponReadServiceImpl extends BaseReadServiceImpl<Coupon> implements CouponReadService {
 
	@Resource
	private CouponReadMapper couponReadMapper;
	
	@Override
	public CouponReadMapper getBaseReadMapper() {
		return couponReadMapper;
	}
	
	public Coupon getDataBySign(String sign) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sign", sign);
		return couponReadMapper.select(map).size()>0?couponReadMapper.select(map).get(0)
				:new Coupon();
	}

}