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
@RequestMapping(value = "/web/index")
public class WebIndexController extends BaseWebController{
	
//	@Autowired
//	private UserReadService userReadService;
//	@Autowired
//	private SendCodeReadService sendCodeReadService;
//	@Autowired
//	private UserWriteService userWriteService;
//	@Autowired
//	private SendCodeWriteService sendCodeWriteService;
//	@Autowired
//	private CloudlinkArticleReadService cloudlinkArticleReadService;
//	
//	@RequestMapping(value = "/test.crx")
//	public String test(
//			HttpServletRequest request, 
//			HttpServletResponse response){
//		try {
//			// 向页面输出文件流
//			response.reset();
//			response.setCharacterEncoding("UTF-8");
//			response.addHeader("Content-Disposition", "attachment;filename=1.crx");
//			OutputStream out = new BufferedOutputStream(response.getOutputStream());
//			response.setContentType("text/plain;charset=UTF-8");
//			FileInputStream f = new FileInputStream(new File("d://sms/tyt-xl.crx"));
//			int b = 0;  
//            byte[] buffer = new byte[1024];  
//            while ((b = f.read(buffer)) != -1) {  
//                // 4.写到输出流(out)中  
//                out.write(buffer, 0, b);  
//            }
//            f.close();
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//	}
	
	@RequestMapping(value = "/index")
	public String index(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		model.addAttribute("menu", "index");
		return "web/index.jsp";
	}
	
	@RequestMapping(value = "/introduce")
	public String introduce(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		model.addAttribute("menu", "introduce");
		return "web/introduce.jsp";
	}
	
	@RequestMapping(value = "/download")
	public String download(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		
		model.addAttribute("menu", "download");
		return "web/download.jsp";
	}
	
	@RequestMapping(value = "/product")
	public String product(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String token = request.getParameter("token");
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		
		model.addAttribute("menu", "product");
		return "web/product.jsp";
	}
	
}
