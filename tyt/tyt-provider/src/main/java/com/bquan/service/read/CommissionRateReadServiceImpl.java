package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.dao.read.CommissionRateReadMapper;
import com.bquan.entity.mysql.CommissionRate;

/**
 * 佣金率 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2016-08-22
 */
public class CommissionRateReadServiceImpl extends BaseReadServiceImpl<CommissionRate> implements CommissionRateReadService{

	@Resource
	private CommissionRateReadMapper commissionRateReadMapper;

	@Override
	public CommissionRateReadMapper getBaseReadMapper() {
		return commissionRateReadMapper;
	}

	public CommissionRate getByUser(String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		List<CommissionRate> commissionRateList = commissionRateReadMapper.select(map);
		return commissionRateList.size()>0?commissionRateList.get(0):null;
	}

	public int getShowRate(String username) {
		CommissionRate commissionRate=getByUser(username);
		if(commissionRate==null){
			commissionRate=getByUser("common");
		}
		if(commissionRate==null){
			return 0;
		}
		return commissionRate.getShowRate();
	}
	
}



