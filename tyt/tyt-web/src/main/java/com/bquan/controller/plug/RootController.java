package com.bquan.controller.plug;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bquan.bean.AjaxResponse;
import com.bquan.entity.mysql.SendCode;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.SendCodeReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.SendCodeWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.MD5Util;
import com.bquan.util.MailUtil;
import com.bquan.util.RandomCodeUtil;
import com.bquan.util.SendCloudUtil;


/**
 * 域名信息
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/plug/root")
public class RootController {
	
	@Autowired
	private SendCodeReadService sendCodeReadService;
	@Autowired
	private SendCodeWriteService sendCodeWriteService;
	@Autowired
    private UserReadService userReadService;
	@Autowired
    private UserWriteService userWriteService;
    
    /**
     * 获取验证码
     * @param username	用户名
     * @param type		操作类型 0代表注册，1代表找回密码
     * @return
     */
	@RequestMapping(value = "/getpasscode")
	@ResponseBody
    public AjaxResponse getpasscode(
    		@RequestParam(value="username",defaultValue="")String username,
    		@RequestParam(value="type",defaultValue="")String type){
    	AjaxResponse ajaxRes = new AjaxResponse();
    	if(StringUtils.isEmpty(username)){
    		ajaxRes.setCode(AjaxResponse.FAILURE);
        	ajaxRes.setMsg("账号不能为空");
        	return ajaxRes;
    	}
    	String code = RandomCodeUtil.getNumberRandomCode(6);
    	boolean flag = false;
    	if(type.equals("0")){
    		User user = userReadService.getUser(username);
    		if(user!=null){
    			ajaxRes.setCode(AjaxResponse.FAILURE);
            	ajaxRes.setMsg("该邮箱地址已注册，请更换邮箱!");
            	return ajaxRes;
    		}
    		flag = MailUtil.sendMail("影盾注册验证码", "您的影盾注册验证码如下:"+code, username);
    	}else if(type.equals("1")){
    		flag = MailUtil.sendMail("影盾登陆密码修改验证码", "您正在进行修改密码操作，验证码为："+code+"，验证码有效期为一个小时！", username);
    	}
    	if(flag){
    		SendCode sendCode = new SendCode();
    		sendCode.setCode(code);
    		sendCode.setIsExpired(1);
    		sendCode.setSendTime(new Date());
    		sendCode.setUsername(username);
    		sendCode.setCreateDate(new Date());
    		sendCode.setModifyDate(new Date());
    		sendCodeWriteService.insert(sendCode);
    		ajaxRes.setCode(AjaxResponse.SUCCESS);
        	ajaxRes.setMsg("验证码发送成功，请登录邮箱查收!");
        	return ajaxRes;
    	}else{
    		ajaxRes.setCode(AjaxResponse.FAILURE);
        	ajaxRes.setMsg("验证码发送失败");
        	return ajaxRes;
    	}
    }
	
	/**
	 * 找回密码
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/getpassword")
	@ResponseBody
    public AjaxResponse getpassword(
    		@RequestParam(value="username",defaultValue="")String username,
    		@RequestParam(value="code",defaultValue="")String code,
    		@RequestParam(value="password",defaultValue="")String password,
    		@RequestParam(value="repassword",defaultValue="")String repassword){
		AjaxResponse ajaxRes = new AjaxResponse();
    	if(StringUtils.isEmpty(username)){
    		ajaxRes.setCode(AjaxResponse.FAILURE);
        	ajaxRes.setMsg("账号不能为空");
        	return ajaxRes;
    	}
    	if(StringUtils.isEmpty(code)){
        	ajaxRes.setMsg("邮箱验证码不能为空！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username", username);
        map.put("orderBy", "sendCode.send_time");
        map.put("orderDesc", "desc");
        List<SendCode> codeList = sendCodeReadService.getTargetList(map);
        
        if(codeList.size()==0){
        	ajaxRes.setMsg("验证码错误！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        // 检验验证码是否过期
        SendCode sendCode = codeList.get(0);
        if((new Date().getTime()-sendCode.getSendTime().getTime())>60*60*1000){
        	ajaxRes.setMsg("验证码已过期，请重新获取！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        // 未查询到验证码或者验证码不匹配
        if(!(code.equals(codeList.get(0).getCode()))){
        	ajaxRes.setMsg("验证码错误！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        if(StringUtils.isEmpty(password)
        		||StringUtils.isEmpty(repassword)){
        	ajaxRes.setMsg("密码为空！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        if(!password.equals(repassword)){
        	ajaxRes.setMsg("密码和确认密码不一致！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
    	
        // 查询用户
        User user = userReadService.getUser(username);
        if(user==null){
        	ajaxRes.setMsg("账号不存在！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        user.setPassword(MD5Util.MD5(password));
        userWriteService.update(user);
        
		ajaxRes.setCode(AjaxResponse.SUCCESS);
    	ajaxRes.setMsg("密码修改成功，请通过新密码登录！");
    	return ajaxRes;
    	
    }
	
	/**
	 * 修改密码
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/changepassword")
	@ResponseBody
    public AjaxResponse changepassword(
    		@RequestParam(value="username",defaultValue="")String username,
    		@RequestParam(value="oldpassword",defaultValue="")String oldpassword,
    		@RequestParam(value="password",defaultValue="")String password,
    		@RequestParam(value="repassword",defaultValue="")String repassword){
		
		System.out.println(oldpassword);
		
		AjaxResponse ajaxRes = new AjaxResponse();
		if(StringUtils.isEmpty(username)
				||StringUtils.isEmpty(oldpassword)
				||StringUtils.isEmpty(password)
				||StringUtils.isEmpty(repassword)){
			ajaxRes.setMsg("参数不能为空！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
		}
		
		// 查询用户
        User user = userReadService.getUser(username);
        if(user==null){
        	ajaxRes.setMsg("账号不存在！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        if(!(MD5Util.MD5(oldpassword).equals(user.getPassword()))){
        	ajaxRes.setMsg("原始密码错误！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
        
        if(!password.equals(repassword)){
        	ajaxRes.setMsg("密码和确认密码不一致！");
    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
    	    return ajaxRes;
        }
		
        user.setPassword(MD5Util.MD5(password));
        userWriteService.update(user);
        ajaxRes.setMsg("密码修改成功！");
	    ajaxRes.setCode(AjaxResponse.SUCCESS);  
	    return ajaxRes;
    }
}
