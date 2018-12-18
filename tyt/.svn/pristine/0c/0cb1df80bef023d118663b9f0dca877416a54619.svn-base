package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.LeaveMessage;
import com.bquan.service.read.LeaveMessageReadService;
import com.bquan.service.write.LeaveMessageWriteService;
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
 * 订单
 * 
 * @author liuxiaokang
 * @createTime 2017-06-25
 */
@RestController
@RequestMapping("/sys/leaveMessage")
public class SysLeaveMessageController extends AbstractController {
	
	@Autowired
	private LeaveMessageReadService leaveMessageReadService;
	@Autowired
	private LeaveMessageWriteService leaveMessageWriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看订单")
	@RequestMapping("/list")
	@RequiresPermissions("sys:leaveMessage:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			params.put("orderBy", "create_time");
			params.put("order", "desc");
			return R.ok().put("page", leaveMessageReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:leaveMessage:info")
	public R info(@PathVariable("id") String id){
		LeaveMessage leaveMessage = leaveMessageReadService.get(id);
		return R.ok().put("leaveMessage", leaveMessage);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存订单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:leaveMessage:save")
	public R save(@RequestBody LeaveMessage leaveMessage){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		leaveMessageWriteService.insert(leaveMessage);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改订单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:leaveMessage:update")
	public R update(@RequestBody LeaveMessage leaveMessage){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = leaveMessage.getId();
		LeaveMessage persistent = leaveMessageReadService.get(id);
		BeanUtils.copyProperties(leaveMessage, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		leaveMessageWriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除订单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:leaveMessage:delete")
	public R delete(String id){
		leaveMessageWriteService.delete(id);
		return R.ok();
	}
}
