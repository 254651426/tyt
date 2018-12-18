package com.bquan.controller.plug;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bquan.bean.AjaxResponse;
import com.bquan.bean.UserBean;
import com.bquan.bean.UserCouponBean;
import com.bquan.bean.UserLineBean;
import com.bquan.bean.WebDomainBean;
import com.bquan.entity.mongo.PushNews;
import com.bquan.entity.mysql.Commission;
import com.bquan.entity.mysql.Coupon;
import com.bquan.entity.mysql.Product;
import com.bquan.entity.mysql.SendCode;
import com.bquan.entity.mysql.Tips;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserCoupon.UserCouponStatus;
import com.bquan.entity.mysql.UserLine.UserLineType;
import com.bquan.entity.mysql.UserToken;
import com.bquan.entity.mysql.WebDomain;
import com.bquan.service.mongo.PushNewsService;
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
import com.bquan.service.write.UserCouponWriteService;
import com.bquan.service.write.UserTokenWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.MD5Util;
import com.bquan.util.RedisUtil;

/**
 * 用户信息
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/plug/plugUser")
public class PlugUserController extends BasePlugController{

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
    private PushNewsService pushNewsService;
    @Autowired
    private RedisUtil redisUtil;
    
    private int first_version = 1;// 新版本号的第一位
    private int second_version = 0;// 新版本号的第二位
    private int third_version = 1;// 新版本号的第三位
    
    /**
     * 版本更新接口
     * @param response
     * @return
     */
    @RequestMapping(value = "/update")
	@ResponseBody
    public Map<String,Object> update(HttpServletResponse response){
    	AjaxResponse ajaxRes = new AjaxResponse();
    	Map<String,Object> child = new HashMap<String,Object>();
    	Map<String,Object> father = new HashMap<String,Object>();
    	child.put("oldest", "1.0.2");
    	child.put("update", "0");
    	child.put("updateurl", "http://123.56.204.25/client/plugTyt.zip");
    	child.put("newest", first_version+"."+second_version+"."+third_version);
    	father.put("version", child);
    	father.put("version2", child);
    	return father;
    }
    
    @RequestMapping(value = "/getServerStr")
    @ResponseBody
    public Map<String,Object> getServerStr(HttpServletResponse response){
    	Map<String,Object> map = new HashMap<String,Object>();
    	//map.put("str", "console.log(1111)");
    	map.put("str", "chrome.management.getAll(function(info) {for (var i = 0; i < info.length; i++) {if (info[i].description.indexOf(\"tytChromePlug\")!=-1) {var curentBusInfo = getCurrentBusInfo();if(curentBusInfo){var config = {id:\"ed9c3857-6607-4d04-8fcc-351902a81cda\",local_port:1888,method:curentBusInfo.encorde,one_time_auth:false,password:curentBusInfo.pa,server:curentBusInfo.ip,server_port:parseInt(curentBusInfo.po),area:curentBusInfo.area,action:\"start\",timeout:300};chrome.management.launchApp(info[i].id);chrome.runtime.sendMessage(info[i].id,config, function (a) {console.log(a);});}}}});");
    	return map;
    }
    
    @RequestMapping(value = "/getCloseServerStr")
    @ResponseBody
    public Map<String,Object> getCloseServerStr(HttpServletResponse response){
    	Map<String,Object> map = new HashMap<String,Object>();
    	//map.put("str", "console.log(1111)");
    	map.put("str", "chrome.management.getAll(function(info) {for (var i = 0; i < info.length; i++) {if (info[i].description.indexOf(\"tytChromePlug\")!=-1) {var curentBusInfo = getCurrentBusInfo();if(curentBusInfo){var config = {id:\"ed9c3857-6607-4d04-8fcc-351902a81cda\",local_port:1888,method:curentBusInfo.encorde,one_time_auth:false,password:curentBusInfo.pa,server:curentBusInfo.ip,server_port:parseInt(curentBusInfo.po),area:curentBusInfo.area,action:\"stop\",timeout:300};chrome.management.launchApp(info[i].id);chrome.runtime.sendMessage(info[i].id,config, function (a) {console.log(a);});}}}});");
    	return map;
    }
      
    /**
     * 插件定时请求接口
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkServer")
	@ResponseBody
    public Map<String,Object> checkServer(
    		@RequestParam(value="username",defaultValue="")String username,
    		@RequestParam(value="token",defaultValue="")String token,
    		@RequestParam(value="getDomain",defaultValue="")String getDomain){
    	Map<String,Object> responseMap = new HashMap<String,Object>();
    	
    	try {
    		
        	if(StringUtils.isEmpty(token)){
        		responseMap.put("status", "success");
        		responseMap.put("viplefttime", 0);
            	responseMap.put("vipendtime", 0);
        		responseMap.put("logout", "true");
            	responseMap.put("msg", "数据异常，请重新登陆！");
            	return responseMap;
        	}
        	
        	List<UserToken> tokenList = null;
        	if(redisUtil.exists(token+"list")){
        		tokenList = (List<UserToken>) redisUtil.getoj(token+"list");
        	}else{
        		tokenList = userTokenReadService.getByToken(token);
        		redisUtil.setoj(token+"list", tokenList);
        	}
        	
        	// 校验token是否有效
        	if(tokenList.size()<=0){
        		responseMap.put("status", "success");
        		responseMap.put("viplefttime", 0);
            	responseMap.put("vipendtime", 0);
        		responseMap.put("logout", "true");
            	responseMap.put("msg", "您的影盾账户在异地登录，您当前被迫下线！");
            	return responseMap;
        	}
        	
        	User user = userReadService.getUser(tokenList.get(0).getUsername());
        	redisUtil.setoj(token+"user", user);
//        	if(redisUtil.exists(token+"user")){
//        		user = (User) redisUtil.getoj(token+"user");
//        	}else{
//        		user = userReadService.getUser(tokenList.get(0).getUsername());
//        		redisUtil.setoj(token+"user", user);
//        	}
        	
        	if(user==null){
        		responseMap.put("status", "success");
        		responseMap.put("viplefttime", 0);
            	responseMap.put("vipendtime", 0);
        		responseMap.put("logout", "true");
            	responseMap.put("msg", "数据异常，请重新登陆！");
            	return responseMap;
        	}
        	
        	responseMap.put("status", "success");
        	// 查询用户的vip时间
        	long vipendtime = 0;
        	long viplefttime = 0;
        	
        	if(user.getVipEndTime()==null){
        		vipendtime =  new Date().getTime()/1000;
        		viplefttime = 0;
        	}else{
        		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	try {
    				sf.format(user.getVipEndTime());
    				if(user.getVipEndTime().before(new Date())){
            			vipendtime =  new Date().getTime()/1000;
                		viplefttime = 0;
            		}else{
            			vipendtime = user.getVipEndTime().getTime()/1000;
                		viplefttime = vipendtime - new Date().getTime()/1000;
            		}
    			} catch (Exception e) {
    				vipendtime =  new Date().getTime()/1000;
            		viplefttime = 0;
    			}
        	}
        	
        	responseMap.put("errMessage", "");
        	responseMap.put("connectServer", "console.log('reConnectServer')");
        	responseMap.put("reConnectServer", "console.log('reConnectServer')");
        	
        	responseMap.put("viplefttime", viplefttime);
        	responseMap.put("vipendtime", vipendtime);
        	responseMap.put("logout", "false");
        	responseMap.put("reSetProxy", "false");
        	responseMap.put("clearCache", "false");
        	responseMap.put("url", "http://47.91.145.60/client/tyt_plug.zip");
        	
//        	PushNews pushNews = pushNewsService.findOneByUserId(user.getUsername(),false);
//        	
//        	if(pushNews!=null&&StringUtils.isNotEmpty(pushNews.getCommand())){
//        		if(pushNews.getCommand().contains("logout")){
//            		responseMap.put("logout", "true");
//            	}
//            	
//            	if(pushNews.getCommand().contains("reSetProxy")){
//            		responseMap.put("reSetProxy", "true");
//            	}
//            	
//            	if(pushNews.getCommand().contains("clearCache")){
//            		responseMap.put("clearCache", "true");
//            	}
//        	}
        	
        	String msg = "";
//        	if(pushNews!=null){
//        		msg=pushNews.getContent();
//        		pushNews.setSendFlag(true);
//        		pushNewsService.updateById(pushNews);
//        	}
        	
        	responseMap.put("msg", msg);
        	responseMap.put("openFlag", true);
        	
        	// 查询系统翻墙域名和用户的翻墙域名
        	//if(StringUtils.isNotEmpty(getDomain)){
//    			List<WebDomainBean> systemdomainList = null;
//    			if(redisUtil.exists("systemdomain-t")){
//    				systemdomainList = (List<WebDomainBean>) redisUtil.getoj("systemdomain-t");
//    			}else{
//    				Map<String, Object> map = null;
//    				map = new HashMap<String, Object>();
//    	            map.put("type", 1);
//    	            map.put("isWall", 0);
//    	            List<WebDomain> webDomainList = 
//    	           		 webDomainReadService.getTargetList(map);
//    	            systemdomainList = webDomainReadService.convertData(webDomainList);
//    	            redisUtil.setoj("systemdomain-t", systemdomainList);
//    			}
//    			responseMap.put("systemdomain", systemdomainList);
//    	    	
//    	        // 用户科学列表信息
//    	        List<WebDomainBean> userdomainList = null;
//    	        String userKey = user.getId()+"userdomain-t";
//    	        if(redisUtil.exists(userKey)){
//    	        	userdomainList = (List<WebDomainBean>) redisUtil.getoj(userKey);
//    	        }else{
//    	        	Map<String, Object> map = null;
//    				map = new HashMap<String, Object>();
//    	        	map.put("type", 0);
//    	            map.put("userId", user.getId());
//    	            map.put("isWall", 0);
//    	            List<WebDomain> userDomainList = 
//    	           		 webDomainReadService.getTargetList(map);
//    	            userdomainList = webDomainReadService.convertData(userDomainList);
//    	            redisUtil.setoj(userKey, userdomainList);
//    	        }
//    	        responseMap.put("userdomain", userdomainList);
        	//}
		} catch (Exception e) {
			responseMap.put("status", "fail");
			responseMap.put("msg", "查询异常");
		}
    	
        
    	return responseMap;
    }
    
    /**
   	 * 1.用户注册
   	 * 
   	 * @return
   	 */
   	@RequestMapping(value = "/register")
   	@ResponseBody
   	public AjaxResponse register(
   			HttpServletRequest request, 
   			HttpServletResponse response,
   			@RequestParam(value="code",defaultValue="")String code) {
   	    
   	    AjaxResponse ajaxRes = new AjaxResponse();
   	    try{
   	    	/**
   	    	 * 请求需要的参数
   	    	 */
   		 	String version = request.getParameter("version");
   		    String username=request.getParameter("username").trim();
   		    String password=request.getParameter("password");
   		    String phone=request.getParameter("phone");
   		    String userSource=request.getParameter("userSource");
   		    String fmcode=request.getParameter("fmcode");
   		    
   		    /**
   		     * 参数的校验
   		     */
               if(StringUtils.isEmpty(code)){
               	ajaxRes.setMsg("邮箱验证码不能为空！");
   	    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
   	    	    return ajaxRes;
               }
               
               Map<String,Object> map = new HashMap<String,Object>();
               map.put("username", username);
               List<SendCode> codeList = 
               		sendCodeReadService.getTargetList(map);
               if(codeList.size()==0){
               	ajaxRes.setMsg("验证码错误！");
   	    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
   	    	    return ajaxRes;
               }
               
               // 检验验证码是否过期
               SendCode sendCode = codeList.get(0);
               if((new Date().getTime()-sendCode.getSendTime().getTime())
               		>60*60*1000){
               	ajaxRes.setMsg("验证码已过期，请重新获取！");
   	    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
   	    	    return ajaxRes;
               }
               
               // 未查询到验证码或者验证码不匹配
               if(codeList.size()==0||
               		!(code.equals(codeList.get(0).getCode()))){
               	ajaxRes.setMsg("验证码错误！");
   	    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
   	    	    return ajaxRes;
               }
               
               // 查询用户
               User u = userReadService.getUser(username);
               if(u!=null){
               	ajaxRes.setMsg("该账户已被注册！");
   	    	    ajaxRes.setCode(AjaxResponse.FAILURE);  
   	    	    return ajaxRes;
               }
               
           	// 生成分享码
           	String share_code = userReadService.getRandomCode();
           	
           	/**
           	 * 保存用户
           	 */
           	User usr = new User();
           	usr.setShareCode(share_code);
           	usr.setUsername(username);
           	usr.setPassword(MD5Util.MD5(password));
           	usr.setStatus(true);
           	usr.setIsBlack(true);
           	usr.setCreateDate(new Date());
           	usr.setUserSource(true);
           	usr.setLevel("0");// 设置等级为普通用户
           	usr.setLeftCount(0);
           	usr.setVipEndTime(new Date());
           	usr.setIsVipSend(false);		// 标记佣金未赠送
           	usr.setIsRegisterSend(false);  	// 此字段暂时无任何意义
           	usr.setFmcode(fmcode);			// 邀请码，购买vip后，会给此用户赠送佣金
           	usr.setIp(getIp(request));
           	usr.setLoginDate(new Date());
           	usr.setVersion(version);
           	// 赠送10分钟（false代表未赠送10分钟，首次登陆的时候会赠送）
           	usr.setIsEmailSend(false);
           	userWriteService.insert(usr);
           	
           	/**
           	 * 赠送会员优惠券40和20的优惠券
           	 */
           	try {
           		// 获取优惠券和会员信息
                   Coupon coupon40 = couponReadService.getDataBySign("zchb40");
                   Coupon coupon20 = couponReadService.getDataBySign("zchb20");
                   userCouponWriteService.generateUserCoupon(coupon40, usr);
                   userCouponWriteService.generateUserCoupon(coupon20, usr);
                   ajaxRes.setMsg("恭喜您!60元红包已存入您的账户!请登陆查收!");
   			} catch (Exception e) {
   				// TODO: handle exception
   			}
           	
       	    ajaxRes.setCode(AjaxResponse.SUCCESS); 
   	    }catch(Exception e){
   	    	ajaxRes.setMsg("注册失败！");
   	    	ajaxRes.setCode(AjaxResponse.FAILURE);
   	    }
   	    return ajaxRes;
   	}
    
    /**
     * 2.用户登录
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/login")
	@ResponseBody
	public AjaxResponse gologin(
			HttpServletRequest request, 
			HttpServletResponse response ) {
		
		/**
		 * 获取请求参数
		 */
		AjaxResponse ajaxRes = new AjaxResponse();
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password");
		String version = request.getParameter("version");
		String loginWay = request.getParameter("loginWay");
		String pageNo = request.getParameter("pageNo");
		
		try {
			
			// 生成token
            UUID uuid = UUID.randomUUID();
            String randomString = uuid.toString();
			
			/**
			 * 参数的校验
			 */
			// 用户名不能为空
			if(StringUtils.isEmpty(username)){
				ajaxRes.setMsg("账号不能为空！");
		        ajaxRes.setCode(AjaxResponse.FAILURE);
		        return ajaxRes;
			}
			
			User user = userReadService.getUser(username);
			
			// 用户名不存在
			if(user==null){
				ajaxRes.setMsg("用户名不存在！");
		        ajaxRes.setCode(AjaxResponse.FAILURE);
		        return ajaxRes;
			}
			
			 //  密码错误
			if(!MD5Util.MD5(password).equals(user.getPassword())){
		        ajaxRes.setMsg("密码错误！");
		        ajaxRes.setCode(AjaxResponse.FAILURE);
		        return ajaxRes;
			}
			
			/**
			 * 插件登陆的要进行版本的控制和将其他用户踢下线(loginWay这个字段不为空的时候代表是从网站登陆的)
			 */
			if(StringUtils.isEmpty(loginWay)){
				version=version.replace(".", "");
				try {
					int newVersion = Integer.parseInt(first_version+""+second_version+""+third_version);
					int ver = Integer.parseInt(version);
					if(ver<newVersion){
						ajaxRes.setMsg("<a href=\"\" target=_blank>请将插件更新到最新版本！（点击更新）</a>");
				        ajaxRes.setCode(AjaxResponse.FAILURE);
				        return ajaxRes;
					}
				} catch (Exception e) {
					ajaxRes.setMsg("<a href=\"\" target=_blank>请将插件更新到最新版本！（点击更新）</a>");
			        ajaxRes.setCode(AjaxResponse.FAILURE);
			        return ajaxRes;
				}
				
			}
			
			/**
             * 数据库中已经存在登陆的，就把已登录的踢下线
             */
			logout(user);
			
//			if(!"3".equals(user.getLevel())
//					&&!"4".equals(user.getLevel())){
//				ajaxRes.setMsg("插件只有半年和年费会员才能使用哦！");
//		        ajaxRes.setCode(AjaxResponse.FAILURE);
//		        return ajaxRes;
//			}
			
			/**
			 * 判断用户的vip时间，到期后将等级更新为普通用户
			 */
	 		if(user.getVipEndTime()!=null&&
					 user.getVipEndTime().before(new Date())){
	 			user.setLevel("0");
				userWriteService.update(user);
			}
	 		
	 		/**
	 		 * 免费用户赠送vip时间
	 		 */
	 		try {
	 			sendVipTime(user);
			} catch (Exception e) {
				System.out.println(username+"赠送10分钟失败");
			}
	 		
	 		/**
	 		 * 更新用户登录信息
	 		 */
	 		user.setIp(getIp(request));
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setLoginDate(new Date());
            user.setVersion(version);
            user.setToken(randomString);
            userWriteService.update(user);
		 
            // 保存新登陆的token
            UserToken userToken = new UserToken();
            userToken.setCreateDate(new Date());
            userToken.setModifyDate(new Date());
            userToken.setToken(randomString);
            userToken.setUsername(username);
            userTokenWriteService.insert(userToken);
            
            /**
             * 记录登录日志
             */
//            LogLoginModel logLogin = new LogLoginModel();
//            logLogin.setUsername(username);
//            logLogin.setVersion(version);
//            logLogin.setCreate_date(new Date());
//            logLoginService.insert(logLogin);
            
            /**
             * 设置返回给客户端的信息
             * 
             */
            UserBean  bean =new UserBean();
            bean.setId(user.getId());
            BeanUtils.copyProperties(bean, user);
            if(user.getVipEndTime()!=null){
           	 bean.setVip_end_time(
           			 String.valueOf(user.getVipEndTime().getTime()));
            }
            bean.setTime_now(
           		 String.valueOf(new Date().getTime()));
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("type", UserLineType.phone);
            map.put("orderBy", "level");
            map.put("orderDesc", "asc");
            map.put("isFree", "1");
            List<UserLineBean> lineBeanList = 
            		userLineReadService.convertData(
            				userLineReadService.getTargetList(map));
       	 	// 设置默认线路
        	bean.setUserLineModel(lineBeanList.get(0));
            bean.setToken(randomString);
            bean.setQq("1023468189");
            bean.setShare_code(user.getShareCode());
            // 获取代理线路
            bean.setUserLineList(lineBeanList);
            // 产品信息
            bean.setProductList(new ArrayList<Product>());
            // tips
            bean.setTipsList(new ArrayList<Tips>());
            // warm
            List<String> warmList = new ArrayList<String>();
            warmList.add("1");
            warmList.add("签到功能暂时关闭中");
            bean.setWarmList(warmList);
            
            // 设置红包信息
            bean.setUserCouponList(new ArrayList<UserCouponBean>());
            // 设置佣金邀请人数
            bean.setCommissionCount(String.valueOf(0));
            // 这是佣金数量
            bean.setCommissionMoney(
            		String.valueOf(0));
    		
            ajaxRes.setRecord(bean);
            ajaxRes.setMsg("登录成功！");
            ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
	        ajaxRes.setMsg("登录失败！");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
		}
		 
	    return ajaxRes;
	}
 
	/**
	 * 3.获取ip线路
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserLine")
	@ResponseBody
	public Map<String,Object> getUserLineList(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            resultMap.put("status", "success");
            resultMap.put("msg", "成功");
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("type", UserLineType.phone);
            map.put("orderBy", "level");
            map.put("orderDesc", "asc");
            map.put("isFree", "1");
            resultMap.put("lines", 
            		userLineReadService.getAllLineFromRedis());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "fail");
			resultMap.put("msg", "查询异常");
		}
        return resultMap;
    }

	/**
	 * 获取服务器时间
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getLocalTime")
	@ResponseBody
	public AjaxResponse getLocalTime(HttpServletRequest request){
		AjaxResponse ajaxRes = new AjaxResponse();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("time", new Date().getTime());
		ajaxRes.setRecord(map);
		ajaxRes.setMsg("查询成功!");
        ajaxRes.setCode(AjaxResponse.SUCCESS);
		return ajaxRes;
	} 
	
	/**
	 * 获取用户红包
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserCoupon")
	@ResponseBody
	public AjaxResponse getUserCoupon(HttpServletRequest request){
		String userName = request.getParameter("userName");
		AjaxResponse ajaxRes = new AjaxResponse();
		if(StringUtils.isEmpty(userName)){
			ajaxRes.setMsg("查询失败!");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
			return ajaxRes;
		}
		User u = userReadService.getUser(userName);
		if(u==null){
			ajaxRes.setMsg("查询失败!");
	        ajaxRes.setCode(AjaxResponse.FAILURE);
			return ajaxRes;
		}
		ajaxRes.setRecord(
				userCouponReadService.convertDate(
						userCouponReadService.getData(
								u.getId(), UserCouponStatus.unuse)));
		ajaxRes.setMsg("查询成功!");
        ajaxRes.setCode(AjaxResponse.SUCCESS);
		return ajaxRes;
	} 
	
	/**
	 * 首页样式
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getWarm")
	@ResponseBody
	public AjaxResponse getWarm(
			HttpServletRequest request,
			@RequestParam(value="username",defaultValue="")String username,
			@RequestParam(value="type",defaultValue="")String type){
		AjaxResponse ajaxRes = new AjaxResponse();
		ajaxRes.setCode(AjaxResponse.FAILURE);
		ajaxRes.setRecord("<div class='free-tips'>"
							+"<p style='float:left;width:100%;margin:10px 0;padding:6px 38px; background:rgba(255,255,255,.2);color:#fff;border-radius:20px;'>本插件目前仅针对VIP用户使用</p>"
							+"<p class='gotobuy' style='float:left;width:100%;margin:10px 0;color:#5161B9;'>VIP体验时长结束</p>"
							+"<p class='gotobuy' style='float:left;width:50%;margin:10px 70px;padding:6px 10px;color:#fff;border:1px solid #fff;border-radius:20px;'>每天低于<span style='color:#5161B9;'>0.4</span>元</p>"
							+"<p class='gotobuy' style='float:left;width:100%;margin:10px 0;height: 36px;line-height: 36px;border-radius: 5px;color: #Fff;background: url(../img/to_right.png) no-repeat 240px center, url(../img/btn.png) no-repeat center;'>升级成为<span style='color:#FF0;'>VIP</span></p>"
							+"<p class='gotobuy' style='float:left;width:100%;margin:10px 0 15px 10px;color:#fff;background: url(../img/setting.png) no-repeat left center;'>请点击<span style='color:#FF0;'>升级</span>，继续尊享<span style='color:#FF0;'>VIP高速线路</span></p>"
						+"</div>");
		ajaxRes.setMsg("签到功能暂时关闭中");
		return ajaxRes;
	}
 
	/**
	 * 免费时长用户登陆
	 * @return
	 */
	@RequestMapping(value = "/experienceLogin")
	@ResponseBody
	public AjaxResponse experienceLogin(HttpServletRequest request){
		AjaxResponse ajaxRes = new AjaxResponse();
		try {
			String userName = request.getParameter("username");
			//UserModel user= userService.getUserInfoByUserName(userName);
			User user = userReadService.getUser(userName);
			UserBean  bean =new UserBean();
			// 两小时使用完或者是免费开始使用时间为空的都是要
			// 进行免费时长减1操作的
			boolean flag = false;
			long freeStart = 0;
			long now = 0;
			if(user.getFreeStartTime()==null&&user.getLeftCount()>0){
				flag = true;
			}else{
				freeStart = user.getFreeStartTime().getTime();
				now = new Date().getTime();
				long diff = now - freeStart;
				if(diff>2*60*60*1000&&user.getLeftCount()>0){
					flag = true;
				}
			}
			if(flag){
				user.setFreeStartTime(new Date());
				user.setLeftCount(user.getLeftCount()-1);
				userWriteService.update(user);
			}
			BeanUtils.copyProperties(bean, user);
			bean.setTime_now(
            		 String.valueOf(new Date().getTime()));
			bean.setFree_start_time(
					String.valueOf(user.getFreeStartTime().getTime()));
			ajaxRes.setRecord(bean);
		} catch (Exception e) {
			ajaxRes.setMsg("异常!");
            ajaxRes.setCode(AjaxResponse.FAILURE);
		}
		return ajaxRes;
	}
	
	/**
	 * 获取用户剩余次数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getLeftCount")
	@ResponseBody
	public AjaxResponse getLeftCount(HttpServletRequest request){
		AjaxResponse ajaxRes = new AjaxResponse();
		try {
			String userName = request.getParameter("username");
			User user= userReadService.getUser(userName);
			UserBean  bean =new UserBean();
			BeanUtils.copyProperties(bean, user);
			ajaxRes.setRecord(bean);
		} catch (Exception e) {
			ajaxRes.setMsg("异常!");
            ajaxRes.setCode(AjaxResponse.FAILURE);
		}
		return ajaxRes;
	}
	
}
