package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bquan.dao.read.SysRoleMenuReadDao;
import com.bquan.service.read.SysRoleMenuReadService;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
public class SysRoleMenuReadServiceImpl implements SysRoleMenuReadService {
	@Autowired
	private SysRoleMenuReadDao sysRoleMenuDao;

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

}
