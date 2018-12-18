package com.bquan.service.write;

import com.bquan.util.Constant.MenuType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bquan.dao.write.SysMenuWriteDao;
import com.bquan.entity.mysql.SysMenuEntity;
import com.bquan.service.write.SysMenuWriteService;
import com.bquan.service.write.SysRoleMenuWriteService;
import com.bquan.service.write.SysUserWriteService;


public class SysMenuWriteServiceImpl implements SysMenuWriteService {
	@Autowired
	private SysMenuWriteDao sysMenuDao;
	@Autowired
	private SysUserWriteService sysUserService;
	@Autowired
	private SysRoleMenuWriteService sysRoleMenuService;
	
	@Override
	public void save(SysMenuEntity menu) {
		sysMenuDao.save(menu);
	}

	@Override
	public void update(SysMenuEntity menu) {
		sysMenuDao.update(menu);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}

}
