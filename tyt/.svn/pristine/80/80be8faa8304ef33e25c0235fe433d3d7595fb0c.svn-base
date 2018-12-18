package com.bquan.controller.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bquan.service.read.CloudlinkArticleReadService;
import com.bquan.service.read.SendCodeReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.SendCodeWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.MailUtil;


/**
 * 网站Controller
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "/web/question")
public class WebQuestionController extends BaseWebController{
	
	@Autowired
	private UserReadService userReadService;
	@Autowired
	private SendCodeReadService sendCodeReadService;
	@Autowired
	private UserWriteService userWriteService;
	@Autowired
	private SendCodeWriteService sendCodeWriteService;
	@Autowired
	private CloudlinkArticleReadService cloudlinkArticleReadService;
	
	@RequestMapping(value = "/index")
	public String question(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		model.addAttribute("menu", "question");
		
		return "web/question.jsp";
	}
	
	@RequestMapping(value = "/getArticleList")
	@ResponseBody
	public Map<String,Object> getArticleList(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String type = request.getParameter("type");
		
		String typeList = "gonggao,windows,mac,linux,android,ios,plugin";
		
		if(typeList.indexOf(type)<0){
			return new HashMap<String,Object>();
		}
		
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("status", type);
		Map<String,Object> responseMap = new HashMap<String,Object>();
		responseMap.put("status", "success");
		responseMap.put("data", cloudlinkArticleReadService.getTargetList(queryMap));
		
		return responseMap;
	}
	
	
	
}
