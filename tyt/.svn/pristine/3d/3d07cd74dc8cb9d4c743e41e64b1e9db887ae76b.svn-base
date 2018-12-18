package com.bquan.controller.web;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bquan.entity.mysql.CommissionRate;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserToken;
import com.bquan.service.read.CommissionRateReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.read.UserTokenReadService;
import com.bquan.service.write.UserTokenWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.AESUtil;

public class BaseWebController {
	
	@Autowired
    private CommissionRateReadService commissionRateReadService;
	@Autowired
    private UserReadService userReadService;
	@Autowired
    private UserWriteService userWriteService;
	@Autowired
    private UserTokenReadService userTokenReadService;
	@Autowired
    private UserTokenWriteService userTokenWriteService;

	/**
	 * 接口返回json信息
	 * @param response
	 * @param obj
	 * @return
	 */
	public String output(HttpServletResponse response,String obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			OutputStream os = response.getOutputStream();
			BufferedWriter bufWriter = new BufferedWriter(
					new OutputStreamWriter(os));
			bufWriter.write(obj);
			bufWriter.flush();
			bufWriter.close();
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 返回加密信息
	 * @param response
	 * @param obj
	 * @return
	 */
	public String outputAes(HttpServletResponse response,String obj) {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
		response.setHeader("Access-Control-Max-Age", "3600");  
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			OutputStream os = response.getOutputStream();
			BufferedWriter bufWriter = new BufferedWriter(
					new OutputStreamWriter(os));
			bufWriter.write(AESUtil.Encrypt(
					obj, 
					"H5gOslZshKZxWikN", 
					"8888199201112173"));
			bufWriter.flush();
			bufWriter.close();
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
     * 获取IP
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request){
    	if (StringUtils.isNotEmpty(request.getHeader("x-forwarded-for"))) { 
    		return request.getHeader("x-forwarded-for");
    	}
    	if(StringUtils.isNotEmpty(request.getRemoteAddr())){
    		return request.getRemoteAddr();
    	}
    	return "获取IP失败"; 
    }
    
    /**
	 * 获取随机时间
	 * @return
	 */
	private static int getRandomTime(int t){
		int time = new Random().nextInt(t);
		if(t>10){
			while(time<10){
				time = new Random().nextInt(t);
			}
		}
		return time;
	}
	
	/**
	 * 更新赠送vip时间
	 * @param user
	 * @param time
	 */
	private void updateVipTime(User user,int time){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+time);
		user.setIsEmailSend(true);
		user.setLevel("5");
		user.setVipEndTime(cal.getTime());
		user.setSendDate(new Date());
		userWriteService.update(user);
	}
    
	/**
	 * 赠送vip时间
	 * @param user
	 */
	public void sendVipTime(User user){
		
		// 非vip用户不赠送时间
		if(!"0".equals(user.getLevel())){
			return;
		}
		
		// 首次赠送 
		if(user.getSendDate()==null){
			CommissionRate commissionRate = 
					commissionRateReadService.getByUser("first");
			if(commissionRate==null){
				// 未查询到数据时默认0分钟
				updateVipTime(user, 0);
			}else{
				updateVipTime(user, commissionRate.getLimitSendTime());
			}
			return;
		}
		
		// 非首次赠送
		CommissionRate commissionRate = null;
		
		String fmcode = user.getFmcode();
		if(StringUtils.isEmpty(fmcode)){
			commissionRate=
					commissionRateReadService.getByUser("common");
		}else{
			User fmUser = 
					userReadService.getByShareCode(fmcode);
			if(fmUser==null){
				commissionRate=
						commissionRateReadService.getByUser("common");
			}else{
				commissionRate=
						commissionRateReadService.getByUser(fmUser.getUsername());
				if(commissionRate==null){
					commissionRate=
							commissionRateReadService.getByUser("common");
				}
			}
		}
		
		int sendMinutes = 0;
		if(commissionRate!=null){
			if(commissionRate.getLimitSendTime()!=null){
				sendMinutes = commissionRate.getLimitSendTime();
			}
		}
		
		// 非首次赠送
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String senddate = sf.format(user.getSendDate());
		String nowdate = sf.format(new Date());
		if(!senddate.equals(nowdate)){
			updateVipTime(user, sendMinutes);
		}
		
	}
	
	// 踢下线
	public void logout(User user){
		List<UserToken> userTokenList = 
        		userTokenReadService.getByUsername(user.getUsername());
        
        // 级别为空的时候，全部踢下线
        if(StringUtils.isEmpty(user.getLevel())){
        	if(userTokenList!=null&&userTokenList.size()>0){
            	for(UserToken uk:userTokenList){
            		userTokenWriteService.delete(uk);
            	}
            }
        }else{
        	// 年费会员支持同时在线两台设备
        	if("4".equals(user.getLevel())){
        		// 已经有2太或者2台以上在线的时候，留4台其他全踢下线
            	if(userTokenList.size()>=2){
            		for(int i=0;i<(userTokenList.size()-1);i++){
                		userTokenWriteService.delete(userTokenList.get(i));
                	}
            	}
            }else if("6".equals(user.getLevel())){
            	// 已经有5太或者5台以上在线的时候，留4台其他全踢下线
            	if(userTokenList.size()>=5){
            		for(int i=0;i<(userTokenList.size()-4);i++){
                		userTokenWriteService.delete(userTokenList.get(i));
                	}
            	}
            }else{
            	if(userTokenList!=null&&userTokenList.size()>0){
                	for(UserToken uk:userTokenList){
                		userTokenWriteService.delete(uk);
                	}
                }
            }
        }
	}
	
}
