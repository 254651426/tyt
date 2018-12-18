package com.bquan.controller.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bquan.entity.mysql.LeaveMessage;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.LeaveMessageReadService;
import com.bquan.service.write.LeaveMessageWriteService;

/**
 * 留言信息接口
 * 
 * @author liusz
 * 
 */
@Controller
@RequestMapping(value = "/web/leaveMessage")
public class WebLeaveMessageController extends BaseWebController{
	
	@Autowired
	private LeaveMessageWriteService leaveMessageWriteService;
	@Autowired
	private LeaveMessageReadService leaveMessageReadService;
	
	/**
	 * 用户添加留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertMessage")
	public String insertMess(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		
		// 获取用户的留言信息
		String info=request.getParameter("info");
		
		// 参数校验
		if(request.getSession().getAttribute("user")==null
				||StringUtils.isEmpty(info)){
			model.addAttribute("msg", "参数不能为空!");
			return "web/login";
		}
		
		try {
			LeaveMessage leaveMessage = new LeaveMessage();
			leaveMessage.setUserId(((User)request.getSession().getAttribute("user")).getId());
			leaveMessage.setInfo(info);
			leaveMessage.setStatus(false);
			leaveMessage.setCreateDate(new Date());
			leaveMessage.setCreateTime(new Date());
			leaveMessageWriteService.insert(leaveMessage);
			
			request.getSession().setAttribute("module", "leaveMessage");
			return "redirect:/web/user/user.html"; 
		} catch (Exception e) {
			model.addAttribute("msg", "操作失败!");
			return "web/user.jsp";
		}
	}
	
}
