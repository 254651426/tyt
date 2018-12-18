package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.Dictionary;
import com.bquan.service.read.DictionaryReadService;
import com.bquan.service.write.DictionaryWriteService;
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
 * 数据字典
 * 
 * @author liuxiaokang
 * @createTime 2017-06-09
 */
@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends AbstractController {
	
	@Autowired
	private DictionaryReadService dictionaryReadService;
	@Autowired
	private DictionaryWriteService dictionaryWriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看数据字典")
	@RequestMapping("/list")
	//@RequiresPermissions("sys:dictionary:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			return R.ok().put("page", dictionaryReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("sys:dictionary:info")
	public R info(@PathVariable("id") String id){
		Dictionary dictionary = dictionaryReadService.get(id);
		return R.ok().put("dictionary", dictionary);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存数据字典")
	@RequestMapping("/save")
	//@RequiresPermissions("sys:dictionary:save")
	public R save(@RequestBody Dictionary dictionary){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		dictionaryWriteService.insert(dictionary);
		dictionaryReadService.loadDateToRedis();
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改数据字典")
	@RequestMapping("/update")
	//@RequiresPermissions("sys:dictionary:update")
	public R update(@RequestBody Dictionary dictionary){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = dictionary.getId();
		Dictionary persistent = dictionaryReadService.get(id);
		BeanUtils.copyProperties(dictionary, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		dictionaryWriteService.update(persistent);
		dictionaryReadService.loadDateToRedis();
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除数据字典")
	@RequestMapping("/delete")
	//@RequiresPermissions("sys:dictionary:delete")
	public R delete(@RequestBody String[] ids){
		dictionaryWriteService.deleteBatch(ids);
		return R.ok();
	}
}
