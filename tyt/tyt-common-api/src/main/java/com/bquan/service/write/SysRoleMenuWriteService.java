package com.bquan.service.write;

import java.util.List;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuWriteService {
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
}
