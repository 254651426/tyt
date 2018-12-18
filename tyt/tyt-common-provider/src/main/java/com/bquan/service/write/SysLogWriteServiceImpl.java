package com.bquan.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bquan.dao.write.SysLogWriteDao;
import com.bquan.entity.mysql.SysLogEntity;
import com.bquan.service.write.SysLogWriteService;

import java.util.List;
import java.util.Map;



public class SysLogWriteServiceImpl implements SysLogWriteService {
	@Autowired
	private SysLogWriteDao sysLogDao;
	
	@Override
	public void save(SysLogEntity sysLog){
		sysLogDao.save(sysLog);
	}
	
	@Override
	public void update(SysLogEntity sysLog){
		sysLogDao.update(sysLog);
	}
	
	@Override
	public void delete(Long id){
		sysLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysLogDao.deleteBatch(ids);
	}
	
}
