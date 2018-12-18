package com.bquan.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bquan.dao.write.SysOssWriteDao;
import com.bquan.entity.mysql.SysOssEntity;
import com.bquan.service.write.SysOssWriteService;

import java.util.List;
import java.util.Map;


public class SysOssWriteServiceImpl implements SysOssWriteService {
	@Autowired
	private SysOssWriteDao sysOssDao;
	
	@Override
	public void save(SysOssEntity sysOss){
		sysOssDao.save(sysOss);
	}
	
	@Override
	public void update(SysOssEntity sysOss){
		sysOssDao.update(sysOss);
	}
	
	@Override
	public void delete(Long id){
		sysOssDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysOssDao.deleteBatch(ids);
	}
	
}
