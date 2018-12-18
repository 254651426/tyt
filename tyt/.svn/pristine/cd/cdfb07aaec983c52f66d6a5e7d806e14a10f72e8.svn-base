package com.bquan.service.write;

import com.alibaba.fastjson.JSON;
import com.bquan.dao.write.SysConfigWriteDao;
import com.bquan.entity.mysql.SysConfigEntity;
import com.bquan.service.write.SysConfigWriteService;
import com.bquan.util.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public class SysConfigWriteServiceImpl implements SysConfigWriteService {
	@Autowired
	private SysConfigWriteDao sysConfigDao;
	
	@Override
	public void save(SysConfigEntity config) {
		sysConfigDao.save(config);
	}

	@Override
	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

}
