package com.bquan.controller.wap;

import com.bquan.annotation.IgnoreAuth;
import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.controller.sys.AbstractController;
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

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商户列表
 * 
 * @author FSY
 * @createTime 2017-06-07
 */
@Controller
@RequestMapping("/wap/test")
public class WapTestController extends AbstractController {
	
	/**
	 * 二维码绑定页面
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		System.out.println("******6666666**");
		
		// 向前台传数据
		model.addAttribute("test", "testName");
		return "wap/test.html";
	}
	
	@RequestMapping("/testJson")
	@ResponseBody
	public R testJson(@RequestBody Map<String,String> map) {
		System.out.println(map.get("ssdf"));
		System.out.println("testJson");
		return R.ok();
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(
			Model model,
			//@RequestParam(name="name",defaultValue="",required=false)String name, // 这种方式get和post参数都可以获取,不加required=false的话，不传此参数会报异常
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse
			) {
		// 这种方式get和post的方式提交的参数都可以获取的到
		//System.out.println(httpServletRequest.getParameter("name")+"============");
		//System.out.println(name+"&&&&&");
		System.out.println("******6666666**");
		
		// 向前台传数据
		model.addAttribute("test", "testName");
		return "wap/test.html";
	}


	
}
