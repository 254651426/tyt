package com.bquan.service.write;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bquan.dao.read.UserReadMapper;
import com.bquan.dao.write.CommissionWriteMapper;
import com.bquan.dao.write.UserWriteMapper;
import com.bquan.entity.mysql.Commission;
import com.bquan.entity.mysql.CommissionRate;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.CommissionRateReadService;
import com.bquan.service.read.TbUserReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.util.BigDecimalCalUtil;
import com.bquan.util.JDBCHelper;
import com.bquan.util.RandomCodeUtil;
import com.bquan.util.RedisUtil;
import com.bquan.util.SpringUtil;


public class UserWriteServiceImpl extends BaseWriteServiceImpl<User> implements UserWriteService {
 
	@Resource
	private UserWriteMapper userWriteMapper;
	@Resource
	private UserReadMapper userReadMapper;
	@Resource
	private CommissionWriteMapper commissionWriteMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	private UserReadService userReadService=
			(UserReadService) SpringUtil.getBean("userReadService");
	private CommissionRateReadService commissionRateReadService=
			(CommissionRateReadService) SpringUtil.getBean("commissionRateReadService");
	
	@Override
	public UserWriteMapper getBaseWriteMapper() {
		return userWriteMapper;
	}
	
	public boolean setCommission(String orderId,String username, BigDecimal commission) {
		try {
			// 校验参数
			if(StringUtils.isEmpty(username)
					||commission==null||commission.intValue()<0){
				return false;
			}
			
			// 通过用户名查询购买vip服务的用户
			User user = userReadService.getUser(username);
			if(user==null){
				return false;
			}
			
			// 检测是否已经赠送过佣金
			if(user.getIsVipSend()==true){
				return false;
			}
			
			// 标记为佣金已赠送
			user.setIsVipSend(true);
			update(user);
			
			// 检测是否有推荐用户
			if(StringUtils.isEmpty(user.getFmcode())){
				return false;
			}
			
			// 查询推荐用户
			String sharecode = user.getFmcode();
			User fmUser = userReadService.getByShareCode(sharecode);
			
			if(fmUser==null){
				return false;
			}
			
			// 查询写入率
			CommissionRate commissionRate = 
					commissionRateReadService.getByUser(fmUser.getUsername());
			if(commissionRate==null){
				// 使用通用写入率
				commissionRate = commissionRateReadService.getByUser("common");
				if(commissionRate==null){
					return false;
				}
			}
			
			// 获取写入率
			int rate = commissionRate.getWriteRate();
			// 随机一位整数，此数字小于写入率，即可写入佣金记录
			int randomNumber = Integer.parseInt(RandomCodeUtil.getNumberRandomCode(1));
			
			// 张哥的账户必须进入佣金记录
			if("ge@zhangge.net".equals(username)
					||"gobi918@vip.qq.com".equals(username)
					||"287988783@qq.com".equals(username)
					||rate>randomNumber){
				if(fmUser.getCommissionAmount()==null
						||fmUser.getCommissionAmount().intValue()<=0){
					fmUser.setCommissionAmount(new BigDecimal(0));
				}
				BigDecimal toCommission = BigDecimalCalUtil.mul(commission, new BigDecimal(commissionRate.getShowRate()));
				toCommission = BigDecimalCalUtil.div(toCommission, new BigDecimal(100), 2);
				fmUser.setCommissionAmount(
						BigDecimalCalUtil.add(fmUser.getCommissionAmount(), toCommission));
				
				update(fmUser);
				// 添加赠送记录
				Commission comm = new Commission();
				comm.setInviteUsername(fmUser.getUsername());
				comm.setUsername(user.getUsername());
				comm.setSendMoney(toCommission);
				comm.setPrice(commission);
				comm.setOrderId(orderId);
				comm.setIsSend(true);
				comm.setCreateDate(new Date());
				comm.setModifyDate(new Date());
				commissionWriteMapper.insert(comm);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void generateShadowsocks() {
		String sql = " select port from account_plugin group by port desc limit 1 ";
		Object number = new JDBCHelper().queryBySql(sql, new Object[]{});
		int port = 50000;
		if(number != null){
			port = Integer.parseInt(number.toString());
		}
		
		int bannianPort = port+1;
		String bannianPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		int bannianCount = 180*24;
		String bannianSql = "insert into account_plugin (type,userId,server,port,password,data,status,autoRemove) values (5,null,null,"+bannianPort+",'"+bannianPassword+"','{\"create\":"+new Date().getTime()+",\"flow\":9999999999999999999,\"limit\":"+bannianCount+"}',0,1)";
		new JDBCHelper().execute(bannianSql, new Object[]{});
		
		int yearPort = port+2;
		String yearPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		int yearCount = 365*24;
		String yearSql = "insert into account_plugin (type,userId,server,port,password,data,status,autoRemove) values (5,null,null,"+yearPort+",'"+yearPassword+"','{\"create\":"+new Date().getTime()+",\"flow\":999999999999999999,\"limit\":"+yearCount+"}',0,1)";
		new JDBCHelper().execute(yearSql, new Object[]{});
		
		int twoYearPort = port+3;
		String twoPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		int twoCount = 730*24;
		String twoSql = "insert into account_plugin (type,userId,server,port,password,data,status,autoRemove) values (5,null,null,"+twoYearPort+",'"+twoPassword+"','{\"create\":"+new Date().getTime()+",\"flow\":99999999999999999,\"limit\":"+twoCount+"}',0,1)";
		new JDBCHelper().execute(twoSql, new Object[]{});
		
		redisUtil.set("bannianPort", String.valueOf(bannianPort));
		redisUtil.set("bannianPassword", String.valueOf(bannianPassword));
		
		redisUtil.set("yearPort", String.valueOf(yearPort));
		redisUtil.set("yearPassword", String.valueOf(yearPassword));
		
		redisUtil.set("twoYearPort", String.valueOf(twoYearPort));
		redisUtil.set("twoPassword", String.valueOf(twoPassword));
	}

}