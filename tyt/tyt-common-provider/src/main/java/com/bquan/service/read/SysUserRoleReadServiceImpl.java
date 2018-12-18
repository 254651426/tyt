package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bquan.dao.read.SysUserRoleReadDao;
import com.bquan.service.read.SysUserRoleReadService;



/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:48
 */
public class SysUserRoleReadServiceImpl implements SysUserRoleReadService {
	@Autowired
	private SysUserRoleReadDao sysUserRoleDao;


	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

}
