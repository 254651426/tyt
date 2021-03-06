package com.bquan.controller.pc;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
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
import org.apache.commons.lang3.StringEscapeUtils;
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
import com.bquan.bean.UserLineBean;
import com.bquan.bean.WebDomainBean;
import com.bquan.entity.mongo.PushNews;
import com.bquan.entity.mysql.UseLog;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserLine;
import com.bquan.entity.mysql.UserLine.UserLineType;
import com.bquan.entity.mysql.UserToken;
import com.bquan.entity.mysql.WebDomain;
import com.bquan.service.mongo.PushNewsService;
import com.bquan.service.read.UserLineReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.read.UserTokenReadService;
import com.bquan.service.read.WebDomainReadService;
import com.bquan.service.write.UseLogWriteService;
import com.bquan.service.write.UserTokenWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.DictionaryUtil;
import com.bquan.util.JsonUtil;
import com.bquan.util.MD5Util;
import com.bquan.util.RedisUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.bquan.controller.util.DateUtil;
/**
 * 用户信息
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/pc/user")
public class PcUserController extends BasePcController {

	@Autowired
	private UserLineReadService userLineReadService;

	@Autowired
	private UserReadService userReadService;

	@Autowired
	private UserWriteService userWriteService;

	@Autowired
	private WebDomainReadService webDomainReadService;

	@Autowired
	private UserTokenReadService userTokenReadService;
	@Autowired
	private UserTokenWriteService userTokenWriteService;
	@Autowired
	private PushNewsService pushNewsService;
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UseLogWriteService useLogWriteService;

	private int first_version = 1;// 新版本号的第一位
	private int second_version = 0;// 新版本号的第二位
	private int third_version = 6;// 新版本号的第三位

	/**
	 * 插件定时请求接口
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/checkServer", method = RequestMethod.POST)
	public String checkServer(HttpServletResponse response,
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "token", defaultValue = "") String token,
			@RequestParam(value = "getDomain", defaultValue = "") String getDomain) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", "success");

		if (StringUtils.isEmpty(token)) {
			responseMap.put("status", "fail");
			return output(response, JsonUtil.toJson(responseMap));
		}

		List<UserToken> tokenList = null;
		if (redisUtil.exists(token + "list")) {
			tokenList = (List<UserToken>) redisUtil.getoj(token + "list");
		} else {
			tokenList = userTokenReadService.getByToken(token);
			redisUtil.setoj(token + "list", tokenList);
		}

		// 校验token是否有效
		if (tokenList == null || tokenList.size() <= 0) {
			responseMap.put("logout", "true");
			responseMap.put("msg", "您的影盾账户在异地登录，您当前被迫下线！");
			return output(response, JsonUtil.toJson(responseMap));
		}

		User user = userReadService.getUser(tokenList.get(0).getUsername());
		redisUtil.setoj(token + "user", user);
		// if(redisUtil.exists(token+"user")){
		// user = (User) redisUtil.getoj(token+"user");
		// }else{
		// user = userReadService.getUser(tokenList.get(0).getUsername());
		// redisUtil.setoj(token+"user", user);
		// }
		if (user == null) {
			responseMap.put("status", "fail");
			return output(response, JsonUtil.toJson(responseMap));
		}

		// 查询用户的vip时间
		long vipendtime = 0;
		long viplefttime = 0;
		if (user.getVipEndTime() == null || user.getVipEndTime().before(new Date())) {
			vipendtime = new Date().getTime() / 1000;
			viplefttime = 0;
		} else {
			vipendtime = user.getVipEndTime().getTime() / 1000;
			viplefttime = vipendtime - new Date().getTime() / 1000;
		}

		responseMap.put("viplefttime", viplefttime);
		responseMap.put("vipendtime", vipendtime);
		responseMap.put("logout", "false");
		responseMap.put("reSetProxy", "false");
		responseMap.put("clearCache", "false");

		PushNews pushNews = pushNewsService.findOneByUserId(user.getUsername(), false);

		if (pushNews != null && StringUtils.isNotEmpty(pushNews.getCommand())) {
			if (pushNews.getCommand().contains("logout")) {
				responseMap.put("logout", "true");
			}

			if (pushNews.getCommand().contains("reSetProxy")) {
				responseMap.put("reSetProxy", "true");
			}

			if (pushNews.getCommand().contains("clearCache")) {
				responseMap.put("clearCache", "true");
			}
		}

		String msg = "";
		if (pushNews != null) {
			msg = pushNews.getContent();
			pushNews.setSendFlag(true);
			pushNewsService.updateById(pushNews);
		}

		responseMap.put("msg", msg);

		// 查询系统翻墙域名和用户的翻墙域名
		// List<WebDomainBean> systemdomainList = null;
		// if(redisUtil.exists("systemdomain-t")){
		// systemdomainList = (List<WebDomainBean>)
		// redisUtil.getoj("systemdomain-t");
		// }else{
		// Map<String, Object> map = null;
		// map = new HashMap<String, Object>();
		// map.put("type", 1);
		// map.put("isWall", 0);
		// List<WebDomain> webDomainList =
		// webDomainReadService.getTargetList(map);
		// systemdomainList = webDomainReadService.convertData(webDomainList);
		// redisUtil.setoj("systemdomain-t", systemdomainList);
		// }
		// responseMap.put("systemdomain", systemdomainList);

		// 用户科学列表信息
		// List<WebDomainBean> userdomainList = null;
		// String userKey = user.getId()+"userdomain-t";
		// if(redisUtil.exists(userKey)){
		// userdomainList = (List<WebDomainBean>) redisUtil.getoj(userKey);
		// }else{
		// Map<String, Object> map = null;
		// map = new HashMap<String, Object>();
		// map.put("type", 0);
		// map.put("userId", user.getId());
		// map.put("isWall", 0);
		// List<WebDomain> userDomainList =
		// webDomainReadService.getTargetList(map);
		// userdomainList = webDomainReadService.convertData(userDomainList);
		// redisUtil.setoj(userKey, userdomainList);
		// }
		// responseMap.put("userdomain", userdomainList);

		return output(response, JsonUtil.toJson(responseMap));
	}

	/**
	 * pc游客登录检查
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/youkecheckServer", method = RequestMethod.POST)
	public String youkecheckServer(HttpServletResponse response,
			@RequestParam(value = "username", defaultValue = "") String username) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(username)) {
			responseMap.put("status", "fail");
			responseMap.put("msg", "参数不能为空");
			return output(response, JsonUtil.toJson(responseMap));
		}
		User user = userReadService.getUser(username);
		if (user == null) {
			responseMap.put("status", "fail");
			responseMap.put("msg", "该用户不存在");
			return output(response, JsonUtil.toJson(responseMap));
		}

		// 查询用户的vip时间
		long vipendtime = 0;
		long viplefttime = 0;
		if (user.getVipEndTime() == null || user.getVipEndTime().before(new Date())) {
			vipendtime = new Date().getTime() / 1000;
			viplefttime = 0;
		} else {
			vipendtime = user.getVipEndTime().getTime() / 1000;
			viplefttime = vipendtime - new Date().getTime() / 1000;
		}

		responseMap.put("viplefttime", viplefttime);
		responseMap.put("vipendtime", vipendtime);
		return output(response, JsonUtil.toJson(responseMap));
	}

	/**
	 * 2.用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String gologin(HttpServletRequest request, HttpServletResponse response) {

		/**
		 * 获取请求参数
		 */
		AjaxResponse ajaxRes = new AjaxResponse();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password");
		String version = request.getParameter("version");
		String loginWay = request.getParameter("loginWay");
		String pageNo = request.getParameter("pageNo");

		try {

			// 生成token
			UUID uuid = UUID.randomUUID();
			String randomString = uuid.toString();

			if ("windows1.0.3".equals(version) || "mac1.0.3".equals(version)) {

			} else {
				ajaxRes.setMsg("您的版本过低，请前往官网http://www.tianyantong.net.cn下载最新版本覆盖安装即可！");
				ajaxRes.setCode(AjaxResponse.FAILURE);
				return output(response, JsonUtil.toJson(ajaxRes));
			}

			/**
			 * 参数的校验
			 */
			// 用户名不能为空
			if (StringUtils.isEmpty(username)) {
				ajaxRes.setMsg("账号不能为空！");
				ajaxRes.setCode(AjaxResponse.FAILURE);
				return output(response, JsonUtil.toJson(ajaxRes));
			}

			User user = userReadService.getUser(username);

			// 用户名不存在
			if (user == null) {
				ajaxRes.setMsg("用户名不存在！");
				ajaxRes.setCode(AjaxResponse.FAILURE);
				return output(response, JsonUtil.toJson(ajaxRes));
			}

			// 密码错误
			if (!MD5Util.MD5(password).equals(user.getPassword())) {
				ajaxRes.setMsg("密码错误！");
				ajaxRes.setCode(AjaxResponse.FAILURE);
				return output(response, JsonUtil.toJson(ajaxRes));
			}

			/**
			 * 数据库中已经存在登陆的，就把已登录的踢下线
			 */
			logout(user);

			/**
			 * 判断用户的vip时间，到期后将等级更新为普通用户
			 */
			if (user.getVipEndTime() != null && user.getVipEndTime().before(new Date())) {
				user.setLevel("0");
				userWriteService.update(user);
			}

			/**
			 * 免费用户赠送vip时间
			 */
			try {
				sendTime(user.getPhonenum());
			} catch (Exception e) {
				System.out.println(username + "赠送10分钟失败");
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
			// redis中保存 token和username的对应关系
			// redisUtil.set(randomString+"-index", username);
			// redisUtil.set(username+"-index", randomString);

			/**
			 * 记录登录日志
			 */
			// LogLoginModel logLogin = new LogLoginModel();
			// logLogin.setUsername(username);
			// logLogin.setVersion(version);
			// logLogin.setCreate_date(new Date());
			// logLoginService.insert(logLogin);

			/**
			 * 设置返回给客户端的信息
			 * 
			 */
			UserBean bean = new UserBean();
			bean.setId(user.getId());
			BeanUtils.copyProperties(bean, user);
			if (user.getVipEndTime() != null) {
				bean.setVip_end_time(String.valueOf(user.getVipEndTime().getTime()));
			}
			bean.setTime_now(String.valueOf(new Date().getTime()));
			bean.setToken(randomString);
			bean.setQq(DictionaryUtil.getDictionaryValue("dictionary_tyt_qq"));
			bean.setShare_code(user.getShareCode());

			ajaxRes.setRecord(bean);
			ajaxRes.setMsg("登录成功！");
			ajaxRes.setCode(AjaxResponse.SUCCESS);
		} catch (Exception e) {
			ajaxRes.setMsg("登录失败！");
			ajaxRes.setCode(AjaxResponse.FAILURE);
		}

		return output(response, JsonUtil.toJson(ajaxRes));
	}

	/**
	 * 获取线路信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getLine", method = RequestMethod.POST)
	public String getLine(HttpServletRequest request, HttpServletResponse response) {
		// String id = request.getParameter("id");
		//
		// if(StringUtils.isNotEmpty(id)){
		// User u = userReadService.get(id);
		// if(u!=null&&u.getStatus()!=null&&u.getStatus()==false){
		// Map<String,Object> map = new HashMap<String,Object>();
		// map.put("orderBy", "level");
		// map.put("orderDesc", "asc");
		//
		// Map<String,Object> responseMap = new HashMap<String,Object>();
		// responseMap.put("userLine", userLineReadService.getTargetList(map));
		// responseMap.put("status", "success");
		// responseMap.put("msg", "成功");
		// return outputAes(response, JsonUtil.toJson(responseMap));
		// }
		// }
		if (!redisUtil.exists("realUserLineData")) {
			userLineReadService.loadAllDataToRedis();
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("userLine", redisUtil.getoj("realUserLineData"));
		responseMap.put("status", "success");
		responseMap.put("msg", "成功");
		return outputAes(response, JsonUtil.toJson(responseMap));
	}
	
	/**
	 * 获取线路信息(C++)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPcLine", method = RequestMethod.POST)
	public String getPcLine(HttpServletRequest request, HttpServletResponse response) {
		// String id = request.getParameter("id");
		//
		// if(StringUtils.isNotEmpty(id)){
		// User u = userReadService.get(id);
		// if(u!=null&&u.getStatus()!=null&&u.getStatus()==false){
		// Map<String,Object> map = new HashMap<String,Object>();
		// map.put("orderBy", "level");
		// map.put("orderDesc", "asc");
		//
		// Map<String,Object> responseMap = new HashMap<String,Object>();
		// responseMap.put("userLine", userLineReadService.getTargetList(map));
		// responseMap.put("status", "success");
		// responseMap.put("msg", "成功");
		// return outputAes(response, JsonUtil.toJson(responseMap));
		// }
		// }
		if (!redisUtil.exists("realUserLineData")) {
			userLineReadService.loadAllDataToRedis();
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("userLine", redisUtil.getoj("realUserLineData"));
		responseMap.put("status", "success");
		responseMap.put("msg", "成功");
		return outputpcAes(response, JsonUtil.toJson(responseMap));
	}

	/**
	 * 中文加密乱码问题的解决
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getName", method = RequestMethod.POST)
	public String getName(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id").trim();
		Map<String, Object> map = new HashMap<String, Object>();
		UserLine userLine = userLineReadService.get(id);
		if (userLine != null) {
			return output(response, userLine.getArea());
		} else {
			return output(response, "");
		}
	}

	/**
	 * 获取线路信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getLineName", method = RequestMethod.POST)
	public String getLineName(HttpServletRequest request, HttpServletResponse response) {

		if (!redisUtil.exists("realUserLineData")) {
			userLineReadService.loadAllDataToRedis();
		}

		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<UserLine> userLineList =
				// userLineReadService.getByType(UserLineType.phone);
				(List<UserLine>) redisUtil.getoj("realUserLineData");

		// String id = request.getParameter("id");
		// if(StringUtils.isNotEmpty(id)){
		// User u = userReadService.get(id);
		// if(u!=null&&u.getStatus()!=null&&u.getStatus()==false){
		// if(u!=null){
		// Map<String,Object> map = new HashMap<String,Object>();
		// map.put("orderBy", "level");
		// map.put("orderDesc", "asc");
		// userLineList = userLineReadService.getTargetList(map);
		// }
		// }
		// }

		List<String> nameList = new ArrayList<String>();
		for (UserLine ul : userLineList) {
			nameList.add(ul.getArea());
		}
		responseMap.put("userLineName", nameList);
		responseMap.put("status", "success");
		responseMap.put("msg", "成功");
		return output(response, JsonUtil.toJson(responseMap));
	}

	// -----------------------------------------------------------------------------------------
	/**
	 * 用户日志
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getUserLog", method = RequestMethod.POST)
	public String getUserLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		JSONArray jsonArr = null;
//		String Loginfo="[{\"userName\":\"254651426@qq.com\",\"phone\":\"18971477097\",\"ip\":\"192.168.0.1\",\"accessAddress\":\"baidu\",\"accessTime\":\"1537406804\"}]";
		String Loginfo = request.getParameter("Logs"); // 用户信息
		String newJson = StringEscapeUtils.unescapeHtml4(Loginfo);//将&quot转义成双引号
//		JSONObject json = net.sf.json.JSONObject.fromObject(newJson);
		jsonArr =  JSONArray.fromObject(newJson); 
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject j = jsonArr.getJSONObject(i);
			UseLog useLog = new UseLog();
			useLog.setCreateTime(new Date());
//			useLog.setUserId(j.getString("userid"));
			useLog.setUserName(j.getString("userName"));
			useLog.setPhone(j.getString("phone"));
			useLog.setIp(j.getString("ip"));
			useLog.setAccessAddress(j.getString("accessAddress"));
//			String date = DateUtil.timeStamp2Date(j.getString("accessTime"),"");  
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			useLog.setAccessTime(DateUtil.timeStamptoDate(j.getString("accessTime")));
			useLog.setCreateTime(new Date());
			useLogWriteService.insert(useLog);
		}
		responseMap.put("status", "success");
		responseMap.put("msg", "成功");
		return output(response, JsonUtil.toJson(responseMap));
	}

}
