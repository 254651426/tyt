package com.bquan.service.read;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.bquan.bean.LeaveMessageBean;
import com.bquan.dao.read.LeaveMessageReadMapper;
import com.bquan.entity.mysql.LeaveMessage;

/**
 * 留言 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public class LeaveMessageReadServiceImpl extends BaseReadServiceImpl<LeaveMessage> implements LeaveMessageReadService{

	@Resource
	private LeaveMessageReadMapper leaveMessageReadMapper;

	@Override
	public LeaveMessageReadMapper getBaseReadMapper() {
		return leaveMessageReadMapper;
	}

	public List<LeaveMessageBean> convertData(List<LeaveMessage> leaveMessageList) {
		List<LeaveMessageBean> resultList = new ArrayList<LeaveMessageBean>();
		for(LeaveMessage lm:leaveMessageList){
			LeaveMessageBean bean = new LeaveMessageBean();
			bean.setInfo(lm.getInfo());
			bean.setCreateTime(lm.getCreateTime());
			bean.setBack_info(lm.getBackInfo());
			resultList.add(bean);
		}
		return resultList;
	}
	
}



