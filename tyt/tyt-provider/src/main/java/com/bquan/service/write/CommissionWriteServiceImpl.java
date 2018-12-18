package com.bquan.service.write;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;

import com.bquan.dao.write.CommissionWriteMapper;
import com.bquan.entity.mysql.Commission;

public class CommissionWriteServiceImpl extends BaseWriteServiceImpl<Commission> implements CommissionWriteService {
 
	@Resource
	private CommissionWriteMapper commissionWriteMapper;

	@Override
	public CommissionWriteMapper getBaseWriteMapper() {
		return commissionWriteMapper;
	}
	
}