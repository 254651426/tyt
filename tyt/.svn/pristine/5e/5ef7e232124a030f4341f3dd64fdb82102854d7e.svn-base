package com.bquan.service.read;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.Commission;

public interface CommissionReadService extends BaseReadService<Commission>{

	/**
	 * 查询记录
	 * @param username
	 * @param isSend true获取佣金记录   false提现记录
	 * @return
	 */
	public List<Commission> getMyCommission(String username,Boolean isSend);
	
	public Integer getSumProduct(String username);
	
	public Integer getSumCommission(String username);
	
	public Integer getSumCommission(String username,Date beginDate,Date endDate);
}
