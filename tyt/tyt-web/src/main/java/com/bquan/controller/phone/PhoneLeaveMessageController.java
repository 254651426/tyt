package com.bquan.controller.phone;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bquan.bean.CommonConstant.Status;
import com.bquan.bean.PhoneLeaveMessageResponse;
import com.bquan.entity.mysql.LeaveMessage;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.LeaveMessageReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.LeaveMessageWriteService;

/**
 * 留言信息接口
 * 
 * @author liusz
 * 
 */
@Controller
@RequestMapping(value = "/phone/phoneLeaveMessage")
public class PhoneLeaveMessageController extends BasePhoneController{
	
	@Autowired
	private LeaveMessageWriteService leaveMessageWriteService;
	@Autowired
	private LeaveMessageReadService leaveMessageReadService;
	@Autowired
	private UserReadService userReadService;
	
	/**
	 * 用户添加留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertMessage")
	@ResponseBody
	public PhoneLeaveMessageResponse insertMess(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取用户名
		String username = request.getParameter("username");
		// 获取用户的留言信息
		String info=request.getParameter("info");
		
		// 参数校验
		if(StringUtils.isEmpty(username)
				||StringUtils.isEmpty(info)){
			return new PhoneLeaveMessageResponse(Status.fail, "参数不能为空!");
		}
		
		try {
			User user = userReadService.getUser(username);
			String info_utf8=new String(info.getBytes("iso8859-1"),"utf-8");
			LeaveMessage leaveMessage = new LeaveMessage();
			leaveMessage.setUserId(user.getId());
			leaveMessage.setInfo(info_utf8);
			leaveMessage.setStatus(false);
			leaveMessage.setCreateDate(new Date());
			leaveMessage.setCreateTime(new Date());
			leaveMessageWriteService.insert(leaveMessage);
			return new PhoneLeaveMessageResponse(Status.success, "成功");
		} catch (Exception e) {
			return new PhoneLeaveMessageResponse(Status.fail, "操作失败!");
		}
	}
	
	/**
	 * 获取用户留言信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAskList")
	@ResponseBody
	public PhoneLeaveMessageResponse getAskList(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取用户名
		String username = request.getParameter("username");
		
		// 参数校验
		if(StringUtils.isEmpty(username)){
			return new PhoneLeaveMessageResponse(Status.fail, "参数不能为空");
		}
		
		// 查询用户留言并返回
		try {
			User user = userReadService.getUser(username);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", user.getId());
			List<LeaveMessage> leaveMessageList = 
					leaveMessageReadService.findPage(1, 20, map).getList();
			
			return new PhoneLeaveMessageResponse(
					Status.success, "成功",
					leaveMessageReadService.convertData(leaveMessageList));
		} catch (Exception e) {
			return new PhoneLeaveMessageResponse(Status.fail, "查询失败");
		}
	}
	
}
