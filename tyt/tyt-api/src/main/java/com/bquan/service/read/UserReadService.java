package com.bquan.service.read;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.User;

public interface UserReadService extends BaseReadService<User>{

	/**
	 * 通过用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User getUser(String username);
	
	/**
	 * 查询当前日期新增用户
	 * @param time
	 * @return
	 */
	public Integer count(String time);
	
	/**
	 * 通过分享码查询用户
	 * @param shareCode
	 * @return
	 */
	public User getByShareCode(String shareCode);
	
	/**
	 * 统计时间段内的用户
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public Integer countUser(Date beginDate,Date endDate,boolean isVip);
	
	public String getRandomCode();
	
	public List<User> selectFmcode(String beginDate,String endDate);
	
	/**
	 *  通过手机唯一标识判断。是否赠送2小时
	 */
	public List<User> selectphonenum(String phonenum);
	
	/**
	 *  通过手机唯一标识判断。把游客的剩余时间给注册后的账号
	 */
	public List<User> selectphonenumtime(String phonenum);
	
}
