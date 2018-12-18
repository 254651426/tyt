package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.WebDomain;
import com.bquan.service.read.WebDomainReadService;
import com.bquan.service.write.WebDomainWriteService;
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
 * @createTime 2017-06-23
 */
@RestController
@RequestMapping("/sys/webDomain")
public class SysWebDomainController extends AbstractController {
	
	@Autowired
	private WebDomainReadService webDomainReadService;
	@Autowired
	private WebDomainWriteService webDomainWriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看数据字典")
	@RequestMapping("/list")
	@RequiresPermissions("sys:webDomain:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			return R.ok().put("page", webDomainReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:webDomain:info")
	public R info(@PathVariable("id") String id){
		WebDomain webDomain = webDomainReadService.get(id);
		return R.ok().put("webDomain", webDomain);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存数据字典")
	@RequestMapping("/save")
	@RequiresPermissions("sys:webDomain:save")
	public R save(@RequestBody WebDomain webDomain){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		webDomainWriteService.insert(webDomain);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改数据字典")
	@RequestMapping("/update")
	@RequiresPermissions("sys:webDomain:update")
	public R update(@RequestBody WebDomain webDomain){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = String.valueOf(webDomain.getId());
		WebDomain persistent = webDomainReadService.get(id);
		BeanUtils.copyProperties(webDomain, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		webDomainWriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除数据字典")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:webDomain:delete")
	public R delete(String id){
		webDomainWriteService.delete(id);
		return R.ok();
	}
}
