package com.bquan.dao.read;

import org.apache.ibatis.annotations.Param;

import com.bquan.entity.mysql.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigReadDao extends BaseReadDao<SysConfigEntity> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
}
