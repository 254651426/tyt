package com.bquan.controller.plug;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bquan.bean.AjaxResponse;
import com.bquan.service.read.ProductReadService;
import com.bquan.service.read.TipsReadService;
import com.bquan.util.JsonUtil;


/**
 * 域名信息
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/plug/product")
public class ProductController {
	
	@Autowired
	private ProductReadService productReadService;
	@Autowired
	private TipsReadService tipsReadService;
    
	/**
	  * 查询产品列表
	  * 
	  * @return
	  */
	@RequestMapping(value = "/query")
	@ResponseBody
	public AjaxResponse addMain(
			HttpServletRequest request, 
			HttpServletResponse response ) {
		AjaxResponse ajaxRes = new AjaxResponse();
		try {
			System.out.println(JsonUtil.toJson(tipsReadService.getAllList()));
			ajaxRes.setRecords(productReadService.getAllList());
		    ajaxRes.setMsg("查询成功！");
		    ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
			ajaxRes.setMsg("查询失败！");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
	    }new Date().getTime();
	    return ajaxRes;
	}
	
	@RequestMapping(value = "/queryProduct")
	@ResponseBody
	public Map<String,Object> queryProduct(
			HttpServletRequest request, 
			HttpServletResponse response ) {
		Map<String,Object> map = new HashMap<String,Object>();
		//AjaxResponse ajaxRes = new AjaxResponse();
		try {
			System.out.println(JsonUtil.toJson(tipsReadService.getAllList()));
			map.put("records", productReadService.getAllList());
			map.put("status", "success");
			map.put("msg", "成功");
		} catch (Exception e) {
			map.put("status", "fail");
			map.put("msg", "失败");
	    }
	    return map;
	}
	
}
