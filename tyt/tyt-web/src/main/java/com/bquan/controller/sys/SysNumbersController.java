package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.Numbers;
import com.bquan.service.read.NumbersReadService;
import com.bquan.service.write.NumbersWriteService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * id生成器
 * 
 * @author liuxiaokang
 * @createTime 2017-06-07
 */
@Controller
@RequestMapping("/sys/numbers")
public class SysNumbersController extends AbstractController {
	
	@Autowired
	private NumbersReadService numbersReadService;
	@Autowired
	private NumbersWriteService numbersWriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看id生成器")
	@RequestMapping("/list")
	@RequiresPermissions("sys:numbers:list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params){
		try {
			return R.ok().put("page", numbersReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	@SysLog("查看id生成器")
	@RequestMapping(value="/sss")
	public String sss(){
		System.out.println("7777");
		try {
			//return R.ok().put("page", numbersReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			//return R.error("查询异常");
		}
		return "qrcode";
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:numbers:info")
	@ResponseBody
	public R info(@PathVariable("id") String id){
		Numbers numbers = numbersReadService.get(id);
		return R.ok().put("numbers", numbers);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存id生成器")
	@RequestMapping("/save")
	@RequiresPermissions("sys:numbers:save")
	public R save(@RequestBody Numbers numbers){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		numbersWriteService.insert(numbers);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改id生成器")
	@RequestMapping("/update")
	@RequiresPermissions("sys:numbers:update")
	public R update(@RequestBody Numbers numbers){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = numbers.getId();
		Numbers persistent = numbersReadService.get(id);
		BeanUtils.copyProperties(numbers, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		numbersWriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除id生成器")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:numbers:delete")
	public R delete(@RequestBody String[] ids){
		numbersWriteService.deleteBatch(ids);
		return R.ok();
	}
}
