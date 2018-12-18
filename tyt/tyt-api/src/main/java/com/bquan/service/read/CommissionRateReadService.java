package com.bquan.service.read;

import com.bquan.entity.mysql.CommissionRate;

/**
 * 佣金率 Service读数据接口
 * @author liuxiaokang
 * @createTime 2016-08-22
 */
public interface CommissionRateReadService extends BaseReadService<CommissionRate>{

	/**
	 * 通过用户名查询
	 * @param username
	 * @return
	 */
	public CommissionRate getByUser(String username);
	
	/**
	 * 查询佣金率
	 * @param username
	 * @return
	 */
	public int getShowRate(String username);
}



