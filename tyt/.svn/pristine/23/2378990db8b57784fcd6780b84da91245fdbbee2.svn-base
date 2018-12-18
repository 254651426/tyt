package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.UserLine;
import com.bquan.service.read.UserLineReadService;
import com.bquan.service.write.UserLineWriteService;
import com.bquan.util.*;
import com.bquan.validator.group.AddGroup;
import com.bquan.validator.group.UpdateGroup;
import com.bquan.validator.Assert;
import com.bquan.validator.ValidatorUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 节点线路
 * 
 * @author liuxiaokang
 * @createTime 2017-06-23
 */
@RestController
@RequestMapping("/sys/userLine")
public class SysUserLineController extends AbstractController {
	
	@Autowired
	private UserLineReadService userLineReadService;
	@Autowired
	private UserLineWriteService userLineWriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看节点线路")
	@RequestMapping("/list")
	@RequiresPermissions("sys:userLine:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			params.put("orderBy", "level");
			params.put("order", "asc");
			return R.ok().put("page", userLineReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:userLine:info")
	public R info(@PathVariable("id") String id){
		UserLine userLine = userLineReadService.get(id);
		return R.ok().put("userLine", userLine);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存节点线路")
	@RequestMapping("/save")
	@RequiresPermissions("sys:userLine:save")
	public R save(@RequestBody UserLine userLine){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		try {
			userLineReadService.loadAllDataToRedis();
			userLineWriteService.insert(userLine);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改节点线路")
	@RequestMapping("/update")
	@RequiresPermissions("sys:userLine:update")
	public R update(@RequestBody UserLine userLine){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		try {
			String id = String.valueOf(userLine.getId());
			UserLine persistent = userLineReadService.get(id);
			BeanUtils.copyProperties(userLine, persistent, 
					new String[] {"id", "createDate", "modifyDate"});
			userLineWriteService.update(persistent);
			userLineReadService.loadAllDataToRedis();
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除节点线路")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:userLine:delete")
	public R delete(String id){
		userLineWriteService.delete(id);
		return R.ok();
	}
}
