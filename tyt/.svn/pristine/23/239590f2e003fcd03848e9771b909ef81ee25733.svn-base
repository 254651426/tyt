package com.bquan.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bquan.dao.read.SysLogReadDao;
import com.bquan.entity.mysql.SysLogEntity;
import com.bquan.service.read.SysLogReadService;

import java.util.List;
import java.util.Map;



public class SysLogReadServiceImpl implements SysLogReadService {
	@Autowired
	private SysLogReadDao sysLogDao;
	
	@Override
	public SysLogEntity queryObject(Long id){
		return sysLogDao.queryObject(id);
	}
	
	@Override
	public List<SysLogEntity> queryList(Map<String, Object> map){
		return sysLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysLogDao.queryTotal(map);
	}
	
}
