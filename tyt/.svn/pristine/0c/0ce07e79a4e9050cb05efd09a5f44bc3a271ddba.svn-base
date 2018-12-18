package com.bquan.controller.plug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bquan.bean.AjaxResponse;
import com.bquan.bean.Pagination;
import com.bquan.bean.WebDomainBean;
import com.bquan.entity.mysql.Tips;
import com.bquan.entity.mysql.WebDomain;
import com.bquan.service.read.TipsReadService;
import com.bquan.service.read.WebDomainReadService;
import com.bquan.service.write.WebDomainWriteService;
import com.bquan.util.JsonUtil;
import com.bquan.util.RedisUtil;

/**
 * 域名信息
 * 
 * @author liuxiaokang
 * 
 */
@Controller
@RequestMapping(value = "/plug/webDomain")
public class WebDomainController {
	
    @Autowired
    private TipsReadService tipsReadService;
    @Autowired
    private WebDomainWriteService webDomainWriteService;
    @Autowired
    private WebDomainReadService webDomainReadService;
    @Autowired
    private RedisUtil redisUtil;
    
    /**
     * 添加用户域名
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add")
	@ResponseBody
	public AjaxResponse addMain(HttpServletRequest request, HttpServletResponse response ) {

		 /**
		  * 获取参数
		  */
	     String domain=request.getParameter("domain");
	     String user_id=request.getParameter("user_id");
	     
	     AjaxResponse ajaxRes = new AjaxResponse();
	     
	     if(StringUtils.isEmpty(domain)
	    		 ||StringUtils.isEmpty(user_id)){
	    	 ajaxRes.setMsg("添加失败！");
	         ajaxRes.setCode(AjaxResponse.FAILURE);
	         return ajaxRes;
	     }
	     
	     try {
			WebDomain webDomain = new WebDomain();
			webDomain.setDomain(domain);
			webDomain.setUserId(user_id);
			webDomain.setCreateDate(new Date());
			webDomain.setType("0");
			webDomain.setStatus("0");
			webDomain.setIsWall("0");
			webDomainWriteService.insert(webDomain);
			
			// 把最新的科学列表信息存进缓存中
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type", 0);
            map.put("userId", webDomain.getUserId());
            map.put("isWall", 0);
            List<WebDomain> userDomainList = 
           		 webDomainReadService.getTargetList(map);
            List<WebDomainBean> userdomainList = 
            		webDomainReadService.convertData(userDomainList);
            redisUtil.setoj(user_id+"userdomain-t", userdomainList);
			
			ajaxRes.setRecord(userdomainList);
            ajaxRes.setMsg("添加成功！");
            ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxRes.setMsg("添加失败！");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
		}
	    return ajaxRes;
    }
 
    /**
     * 获取用户域名
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/getUserDomain")
	@ResponseBody
	public AjaxResponse getUserDomain(HttpServletRequest request, HttpServletResponse response) {
	
        AjaxResponse ajaxRes = new AjaxResponse();
        
        /**
         * 获取参数
         */
        String  type=request.getParameter("type");//0个人 1系统   
        String  user_id=request.getParameter("user_id");
        String  is_wall=request.getParameter("is_wall");//0 需要翻墙 1 不需要翻墙
        
        try {
        	 Pagination page = new Pagination();
        	 String key = "";
        	 if("0".equals(type)){
        		 key = user_id+"userdomain-t";
        	 }else{
        		 key = "systemdomain-t";
        	 }
        	 List<WebDomainBean> userdomainList = null;
        	 if(redisUtil.exists(key)){
        		 userdomainList = (List<WebDomainBean>) redisUtil.getoj(key);
        	 }else{
        		 Map<String, Object> map = new HashMap<String, Object>();
        		 map.put("type", type);
        		 map.put("userId", user_id);
        		 map.put("isWall", is_wall);
        		 List<WebDomain> webDomainList = 
        				 webDomainReadService.getTargetList(map);
        		 userdomainList = 
                 		webDomainReadService.convertData(webDomainList);
        		 redisUtil.setoj(key, userdomainList);
        	 }
        	 page.setResults(userdomainList);
             ajaxRes.setPage(page);
             ajaxRes.setMsg("查询成功!");
             ajaxRes.setCode(AjaxResponse.SUCCESS);
        }catch (Exception e) {
            ajaxRes.setMsg("查询失败!");
            ajaxRes.setCode(AjaxResponse.FAILURE);
            e.printStackTrace();
        }
        return ajaxRes;
	}

	/**
	 * 删除用户域名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteUserDom")
	@ResponseBody
	public AjaxResponse deleteUserDom(HttpServletRequest request, HttpServletResponse response) {
	
		AjaxResponse ajaxRes = new AjaxResponse();
		
	    // 域名的id
	    String id=request.getParameter("id");
	    if(StringUtils.isEmpty(id)){
	    	ajaxRes.setMsg("请求参数异常!");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
	    	return  ajaxRes ;
	    }
	    try {
			WebDomain webDomain = webDomainReadService.get(id);
			if(webDomain==null){
				ajaxRes.setMsg("该域名不存在!");
		        ajaxRes.setCode(AjaxResponse.FAILURE);
		    	return  ajaxRes ;
			}
			webDomainWriteService.delete(webDomain);
			
			// 把最新的科学列表信息存进缓存中
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type", 0);
            map.put("userId", webDomain.getUserId());
            map.put("isWall", 0);
            List<WebDomain> userDomainList = 
              		 webDomainReadService.getTargetList(map);
            List<WebDomainBean> userdomainList = 
               		webDomainReadService.convertData(userDomainList);
            // 保存数据到redis中
            redisUtil.setoj(webDomain.getUserId()+"userdomain-t", userdomainList);
   			
			ajaxRes.setMsg("删除成功!");
	        ajaxRes.setCode(AjaxResponse.SUCCESS); 
		} catch (Exception e) {
			ajaxRes.setMsg("删除失败!");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
		}
	    return ajaxRes;
	}

	@RequestMapping(value = "/getTips")
	@ResponseBody
	public String getTips(HttpServletRequest request, HttpServletResponse response){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Tips> tipsList = tipsReadService.getAllList();
		for(Tips tips:tipsList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",tips.getId() );
			map.put("url", tips.getUrl());
			map.put("txt", tips.getContent());
			list.add(map);
		}
		try {
			response.getWriter().println(JsonUtil.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
