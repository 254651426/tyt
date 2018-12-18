package com.bquan.service.write;

import java.util.List;



/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleWriteService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	void delete(Long userId);
}
