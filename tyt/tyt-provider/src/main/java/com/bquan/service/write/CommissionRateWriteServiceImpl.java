package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.CommissionRateWriteMapper;
import com.bquan.entity.mysql.CommissionRate;

/**
 * 佣金率 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-22
 */
public class CommissionRateWriteServiceImpl extends BaseWriteServiceImpl<CommissionRate> implements CommissionRateWriteService{

	@Resource
	private CommissionRateWriteMapper commissionRateWriteMapper;

	@Override
	public CommissionRateWriteMapper getBaseWriteMapper() {
		return commissionRateWriteMapper;
	}
	
}