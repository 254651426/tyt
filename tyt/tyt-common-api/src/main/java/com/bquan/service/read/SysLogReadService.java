package com.bquan.service.read;

import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.SysLogEntity;

/**
 * 系统日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40s:56
 */
public interface SysLogReadService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
}
