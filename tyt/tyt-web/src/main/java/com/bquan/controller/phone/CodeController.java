package com.bquan.controller.phone;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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

import com.bquan.entity.mysql.SendCode;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.CommissionRateReadService;
import com.bquan.service.read.CommissionReadService;
import com.bquan.service.read.CouponReadService;
import com.bquan.service.read.ProductReadService;
import com.bquan.service.read.SendCodeReadService;
import com.bquan.service.read.TipsReadService;
import com.bquan.service.read.UserCouponReadService;
import com.bquan.service.read.UserLineReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.read.UserTokenReadService;
import com.bquan.service.read.WebDomainReadService;
import com.bquan.service.write.SendCodeWriteService;
import com.bquan.service.write.UserCouponWriteService;
import com.bquan.service.write.UserTokenWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.PhoneCodeUtil;
import com.bquan.util.RandomCodeUtil;


/**
 * 用户信息
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/phone/code")
public class CodeController extends BasePhoneController{

    @Autowired
    private UserLineReadService userLineReadService;
    @Autowired
    private ProductReadService productReadService;
    @Autowired
    private UserReadService userReadService;
    @Autowired
    private CouponReadService couponReadService;
    @Autowired
    private UserCouponReadService userCouponReadService;
    @Autowired
    private UserCouponWriteService userCouponWriteService;
    @Autowired
    private CommissionReadService commissionReadService;
    @Autowired
    private UserWriteService userWriteService;
    @Autowired
    private CommissionRateReadService commissionRateReadService;
    @Autowired
    private TipsReadService tipsReadService;
    @Autowired
    private WebDomainReadService webDomainReadService;
    @Autowired
    private SendCodeReadService sendCodeReadService;
    @Autowired
    private UserTokenReadService userTokenReadService;
    @Autowired
    private UserTokenWriteService userTokenWriteService;
    @Autowired
    private SendCodeWriteService sendCodeWriteService;
    
    /**
	 * 1.获取验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPhoneCode")
	@ResponseBody
	public Map<String,Object> getPhoneCode(
			HttpServletRequest request, 
			HttpServletResponse response) {
	    
		Map<String,Object> responseMap = 
				new HashMap<String,Object>();
    	/**
    	 * 请求需要的参数
    	 */
	    String username=request.getParameter("username").trim();
	    String type=request.getParameter("type");
	    
    	if(StringUtils.isEmpty(username)
    			||StringUtils.isEmpty(type)){
    		responseMap.put("status", "fail");
    		responseMap.put("msg", "参数不能为空");
    		return responseMap;
    	}
    	
    	// 生成验证码
    	String code = RandomCodeUtil.getNumberRandomCode(6);
    	User user = userReadService.getUser(username);
    	// 发送内容
    	String content = "";
    	
    	if("register".equals(type)){
    		if(user!=null){
    			responseMap.put("status", "fail");
        		responseMap.put("msg", "账号已被注册");
        		return responseMap;
    		}
    		content = "您的影盾注册验证码如下:"+code;
    	}else if("changepassword".equals(type)){
    		if(user==null){
    			responseMap.put("status", "fail");
        		responseMap.put("msg", "账号不存在");
        		return responseMap;
    		}
    		content = "您正在进行修改密码操作，验证码为："+code+"，验证码有效期为一个小时！";
    	}else{
    		responseMap.put("status", "fail");
    		responseMap.put("msg", "type参数错误");
    		return responseMap;
    	}
    	
    	if(PhoneCodeUtil.sendCode(username, content)){
    		SendCode sendCode = new SendCode();
    		sendCode.setCode(code);
    		sendCode.setIsExpired(1);
    		sendCode.setSendTime(new Date());
    		sendCode.setUsername(username);
    		sendCode.setCreateDate(new Date());
    		sendCode.setModifyDate(new Date());
    		sendCodeWriteService.insert(sendCode);
    		responseMap.put("code", code);
    		responseMap.put("status", "success");
    		responseMap.put("msg", "成功");
    		return responseMap;
    	}else{
    		responseMap.put("status", "fail");
    		responseMap.put("msg", "验证码发送给失败");
    		return responseMap;
    	}
	}
    
}
