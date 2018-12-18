package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.CommissionRate;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.CommissionRateReadService;
import com.bquan.service.read.CommissionReadService;
import com.bquan.service.read.OrdersReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.CommissionRateWriteService;
import com.bquan.util.*;
import com.bquan.validator.group.AddGroup;
import com.bquan.validator.group.UpdateGroup;
import com.bquan.validator.Assert;
import com.bquan.validator.ValidatorUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 佣金率
 * 
 * @author liuxiaokang
 * @createTime 2017-06-25
 */
@RestController
@RequestMapping("/sys/commissionRate")
public class SysCommissionRateController extends AbstractController {
	
	@Autowired
	private CommissionRateReadService commissionRateReadService;
	@Autowired
	private CommissionRateWriteService commissionRateWriteService;
	@Autowired
	private UserReadService userReadService;
	@Autowired
	private CommissionReadService commissionReadService;
	@Autowired
	private OrdersReadService ordersReadService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看佣金率")
	@RequestMapping("/list")
	@RequiresPermissions("sys:commissionRate:list")
	public R list(@RequestParam Map<String, Object> params,HttpServletRequest request){
		try {
			String queryTime = request.getParameter("queryTime");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Pager pager = commissionRateReadService.findPager(new Pager(params));
			Calendar calendar = Calendar.getInstance();
			if(org.apache.commons.lang.StringUtils.isNotEmpty(queryTime)){
				Date d = null;
				try {
					d = sf.parse(queryTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				calendar.setTime(d);
			}
			queryTime = sf.format(calendar.getTime());
			calendar.set(
					calendar.get(Calendar.YEAR), 
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			Date beginD = calendar.getTime();
			calendar.set(
					calendar.get(Calendar.YEAR), 
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			Date endD = calendar.getTime();
			for(CommissionRate cr:(List<CommissionRate>)pager.getResult()){
				User user = userReadService.getUser(cr.getUsername());
				if(user==null){
					continue;
				}
				cr.setProductAmount(
						String.valueOf(
								commissionReadService.getSumCommission(
										cr.getUsername(), 
										DateOperateUtil.getFirstDayOfMonth(calendar).getTime(), 
										DateOperateUtil.getLastDayOfMonth(calendar).getTime())));
				cr.setSendAmount(
						String.valueOf(
								commissionReadService.getSumCommission(
										cr.getUsername(), 
										DateOperateUtil.getFirstDayOfMonth(calendar).getTime(), 
										DateOperateUtil.getLastDayOfMonth(calendar).getTime())
								*
								commissionRateReadService.getShowRate(cr.getUsername())/100));
				cr.setTodayAmount(String.valueOf(
						commissionReadService.getSumCommission(cr.getUsername(), beginD, endD)
						*
						commissionRateReadService.getShowRate(cr.getUsername())/100));
				cr.setRealAmount(String.valueOf(
						ordersReadService.sumRealCommissionPrice(
								user.getShareCode(), 
								sf_date.format(DateOperateUtil.getFirstDayOfMonth(calendar).getTime()), 
								sf_date.format(DateOperateUtil.getLastDayOfMonth(calendar).getTime()))));
			}
			return R.ok().put("page", pager);
		} catch (Exception e) {
			return R.error("查询异常");
		}
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:commissionRate:info")
	public R info(@PathVariable("id") String id){
		CommissionRate commissionRate = commissionRateReadService.get(id);
		return R.ok().put("commissionRate", commissionRate);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存佣金率")
	@RequestMapping("/save")
	@RequiresPermissions("sys:commissionRate:save")
	public R save(@RequestBody CommissionRate commissionRate){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		commissionRateWriteService.insert(commissionRate);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改佣金率")
	@RequestMapping("/update")
	@RequiresPermissions("sys:commissionRate:update")
	public R update(@RequestBody CommissionRate commissionRate){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = commissionRate.getId();
		CommissionRate persistent = commissionRateReadService.get(id);
		BeanUtils.copyProperties(commissionRate, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		commissionRateWriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除佣金率")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:commissionRate:delete")
	public R delete(String id){
		commissionRateWriteService.delete(id);
		return R.ok();
	}
}
