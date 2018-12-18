package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.controller.sys.AbstractController;
import com.bquan.entity.mysql.SysConfigEntity;
import com.bquan.service.mongo.LogService;
import com.bquan.service.read.DictionaryReadService;
import com.bquan.service.read.SysConfigReadService;
import com.bquan.service.write.SysConfigWriteService;
import com.bquan.util.JsonUtil;
import com.bquan.util.PageUtils;
import com.bquan.util.Query;
import com.bquan.util.R;
import com.bquan.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	
	@Autowired
	private SysConfigReadService sysConfigReadService;
	@Autowired
	private SysConfigWriteService sysConfigWriteService;
	@Autowired
	private DictionaryReadService dictionaryReadService;
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params){
		//System.out.println(JsonUtil.toJson(params));
		//查询列表数据
		Query query = new Query(params);
		List<SysConfigEntity> configList = sysConfigReadService.queryList(query);
		int total = sysConfigReadService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigReadService.queryObject(id);
		
		return R.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigWriteService.save(config);
		
		return R.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigWriteService.update(config);
		
		return R.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody Long[] ids){
		sysConfigWriteService.deleteBatch(ids);
		
		return R.ok();
	}

}
