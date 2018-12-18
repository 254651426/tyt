package com.bquan.service.read;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bquan.dao.read.NumbersReadMapper;
import com.bquan.entity.mysql.Numbers;

/**
 * 编号表 Service层读接口实现
 * @author FSY
 * @createTime 2017-05-12
 */
public class NumbersReadServiceImpl extends BaseReadServiceImpl<Numbers> implements NumbersReadService{

	@Resource
	private NumbersReadMapper numbersReadMapper;

	@Override
	public NumbersReadMapper getBaseReadMapper() {
		return numbersReadMapper;
	}
	
	
}



