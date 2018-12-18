package com.bquan.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bquan.dao.read.SysOssReadDao;
import com.bquan.entity.mysql.SysOssEntity;
import com.bquan.service.read.SysOssReadService;

import java.util.List;
import java.util.Map;


public class SysOssReadServiceImpl implements SysOssReadService {
	@Autowired
	private SysOssReadDao sysOssDao;
	
	@Override
	public SysOssEntity queryObject(Long id){
		return sysOssDao.queryObject(id);
	}
	
	@Override
	public List<SysOssEntity> queryList(Map<String, Object> map){
		return sysOssDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysOssDao.queryTotal(map);
	}
	
}
