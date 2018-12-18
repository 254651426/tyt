package com.bquan.service.read;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.bquan.dao.read.TipsReadMapper;
import com.bquan.entity.mysql.Tips;


/**
 * 提示语 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2016-08-26
 */
public class TipsReadServiceImpl extends BaseReadServiceImpl<Tips> implements TipsReadService{

	@Resource
	private TipsReadMapper tipsReadMapper;

	@Override
	public TipsReadMapper getBaseReadMapper() {
		return tipsReadMapper;
	}

}



