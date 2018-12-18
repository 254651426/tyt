package com.bquan.dao.read;

import java.util.List;

import com.bquan.entity.mysql.SysMenuEntity;

/**
 * 节点线路 DAO读接口
 * @author liuxiaokang
 * @createTime 2016-08-24
 */
public interface UserLineReadMapper<UserLine> extends BaseReadMapper<UserLine>{
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<UserLine> queryListParentId(int parentId);
	
    
}
