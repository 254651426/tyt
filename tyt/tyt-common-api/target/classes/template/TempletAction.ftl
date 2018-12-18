package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.${clazzinfo.classname};
import com.bquan.service.read.${clazzinfo.classname}ReadService;
import com.bquan.service.write.${clazzinfo.classname}WriteService;
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
 * ${clazzinfo.objname}
 * 
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
@RestController
@RequestMapping("/sys/${clazzinfo.tableas}")
public class Sys${clazzinfo.classname}Controller extends AbstractController {
	
	@Autowired
	private ${clazzinfo.classname}ReadService ${clazzinfo.tableas}ReadService;
	@Autowired
	private ${clazzinfo.classname}WriteService ${clazzinfo.tableas}WriteService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看${clazzinfo.objname}")
	@RequestMapping("/list")
	@RequiresPermissions("sys:${clazzinfo.tableas}:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			return R.ok().put("page", ${clazzinfo.tableas}ReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:${clazzinfo.tableas}:info")
	public R info(@PathVariable("id") String id){
		${clazzinfo.classname} ${clazzinfo.tableas} = ${clazzinfo.tableas}ReadService.get(id);
		return R.ok().put("${clazzinfo.tableas}", ${clazzinfo.tableas});
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存${clazzinfo.objname}")
	@RequestMapping("/save")
	@RequiresPermissions("sys:${clazzinfo.tableas}:save")
	public R save(@RequestBody ${clazzinfo.classname} ${clazzinfo.tableas}){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		${clazzinfo.tableas}WriteService.insert(${clazzinfo.tableas});
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改${clazzinfo.objname}")
	@RequestMapping("/update")
	@RequiresPermissions("sys:${clazzinfo.tableas}:update")
	public R update(@RequestBody ${clazzinfo.classname} ${clazzinfo.tableas}){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = ${clazzinfo.tableas}.getId();
		${clazzinfo.classname} persistent = ${clazzinfo.tableas}ReadService.get(id);
		BeanUtils.copyProperties(${clazzinfo.tableas}, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		${clazzinfo.tableas}WriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除${clazzinfo.objname}")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:${clazzinfo.tableas}:delete")
	public R delete(String id){
		${clazzinfo.tableas}WriteService.delete(id);
		return R.ok();
	}
}
