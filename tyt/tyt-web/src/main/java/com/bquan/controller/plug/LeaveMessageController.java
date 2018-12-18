package com.bquan.controller.plug;

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

import com.bquan.bean.AjaxResponse;
import com.bquan.entity.mysql.LeaveMessage;
import com.bquan.service.read.LeaveMessageReadService;
import com.bquan.service.write.LeaveMessageWriteService;
import com.bquan.util.JsonUtil;

/**
 * 留言信息接口
 * 
 * @author liusz
 * 
 */
@Controller
@RequestMapping(value = "/plug/leaveMessage")
public class LeaveMessageController extends BasePlugController{
	
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
	@ResponseBody
	public AjaxResponse insertMess(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResponse ajaxRes = new AjaxResponse();
		// 获取用户的id
		String userId = request.getParameter("userid");
		// 获取用户的留言信息
		String info=request.getParameter("info");
		
		// 参数校验
		if(StringUtils.isEmpty(userId)
				||StringUtils.isEmpty(info)){
			ajaxRes.setMsg("参数不能为空!");
			ajaxRes.setCode(AjaxResponse.FAILURE);
			return ajaxRes;
		}
		
		try {
//			String info_utf8=new String(info.getBytes("iso8859-1"),"utf-8");
			LeaveMessage leaveMessage = new LeaveMessage();
			leaveMessage.setUserId(userId);
			leaveMessage.setInfo(info);
			leaveMessage.setStatus(false);
			leaveMessage.setCreateDate(new Date());
			leaveMessage.setCreateTime(new Date());
			leaveMessageWriteService.insert(leaveMessage);
			ajaxRes.setMsg("操作成功!");
			ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
			ajaxRes.setMsg("操作失败!");
			ajaxRes.setCode(AjaxResponse.FAILURE);
		}
		return ajaxRes;
	}
	
	/**
	 * 获取用户留言信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAskList")
	@ResponseBody
	public AjaxResponse getAskList(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResponse ajaxRes = new AjaxResponse();
		// 获取用户的id
		String userId = request.getParameter("userid");
		
		// 参数校验
		if(StringUtils.isEmpty(userId)){
			ajaxRes.setMsg("参数不能为空!");
			ajaxRes.setCode(AjaxResponse.FAILURE);
			return ajaxRes;
		}
		
		// 查询用户留言并返回
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<LeaveMessage> leaveMessageList = 
					leaveMessageReadService.findPage(1, 20, map).getList();
			System.out.println(JsonUtil.toJson(leaveMessageReadService.convertData(leaveMessageList)));
			ajaxRes.setRecords(leaveMessageReadService.convertData(leaveMessageList));
			ajaxRes.setMsg("查询成功!");
			ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxRes.setMsg("查询失败!");
			ajaxRes.setCode(AjaxResponse.FAILURE);
		}
		return ajaxRes;
	}
	
}
