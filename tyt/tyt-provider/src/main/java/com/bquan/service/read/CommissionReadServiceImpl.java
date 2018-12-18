package com.bquan.service.read;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.bquan.dao.read.CommissionReadMapper;
import com.bquan.entity.mysql.Commission;

public class CommissionReadServiceImpl extends BaseReadServiceImpl<Commission> implements CommissionReadService {
 
	@Resource
	private CommissionReadMapper commissionReadMapper;
	
	@Override
	public CommissionReadMapper getBaseReadMapper() {
		return commissionReadMapper;
	}

	public List<Commission> getMyCommission(String username,Boolean isSend){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inviteUsername", username);
		map.put("isSend", isSend);
		map.put("extraSql", " and date_format(commission.create_date,'%Y-%m')=date_format(now(),'%Y-%m') ");
		map.put("orderBy", "commission.create_date");
		map.put("orderDesc", "desc");
		return commissionReadMapper.select(map);
	}

	public Integer getSumProduct(String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sumItem", "price");
		map.put("inviteUsername", username);
		map.put("extraSql", " and date_format(commission.create_date,'%Y-%m')=date_format(now(),'%Y-%m') ");
		BigDecimal su =commissionReadMapper.sum(map);
		return su==null?0:su.intValue();
	}

	public Integer getSumCommission(String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sumItem", "send_money");
		map.put("inviteUsername", username);
		return commissionReadMapper.sum(map).intValue();
	}

	public Integer getSumCommission(String username, Date beginDate, Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("sumItem", "price");
		map.put("inviteUsername", username);
		map.put("timeBy", "commission.create_date");
		map.put("beginDate", sf.format(beginDate));
		map.put("endDate", sf.format(endDate));
		BigDecimal sumNum = commissionReadMapper.sum(map);
		if(sumNum==null){
			return 0;
		}else{
			return sumNum.intValue();
		}
	}

}