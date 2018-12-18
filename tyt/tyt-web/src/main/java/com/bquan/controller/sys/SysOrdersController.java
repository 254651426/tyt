package com.bquan.controller.sys;

import com.bquan.annotation.SysLog;
import com.bquan.bean.Pager;
import com.bquan.entity.mysql.Orders;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.OrdersReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.OrdersWriteService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单
 * 
 * @author liuxiaokang
 * @createTime 2017-06-24
 */
@RestController
@RequestMapping("/sys/orders")
public class SysOrdersController extends AbstractController {
	
	@Autowired
	private OrdersReadService ordersReadService;
	@Autowired
	private OrdersWriteService ordersWriteService;
	@Autowired
	private UserReadService userReadService;
	
	/**
	 * 所有用户列表
	 */
	@SysLog("查看订单")
	@RequestMapping("/list")
	@RequiresPermissions("sys:orders:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			params.put("orderBy", "orders.create_date");
			params.put("order", "desc");
			return R.ok().put("page", ordersReadService.findPager(new Pager(params)));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("查询异常");
		}
	}
	
	@RequestMapping("/statistics")
	@RequiresPermissions("sys:orders:statistics")
	@ResponseBody
	public Map<String,Object> statistics(){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf_day = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf_month = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sf_year = new SimpleDateFormat("yyyy");
		map.put("dayPay",ordersReadService.sumOrderPrice(2, sf_day.format(new Date())).toString());
		map.put("monthPay",ordersReadService.sumOrderPrice(2, sf_month.format(new Date())).toString());
		map.put("yearPay",ordersReadService.sumOrderPrice(2, sf_year.format(new Date())).toString());
		
		/**
		 * 用户统计
		 */
		map.put("newUser",String.valueOf(userReadService.countUser(new Date(), new Date(), false)));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		
		Calendar nextDay = Calendar.getInstance();
		nextDay.set(Calendar.HOUR_OF_DAY, 23);
		nextDay.set(Calendar.MINUTE, 59);
		nextDay.set(Calendar.SECOND, 59);
		List<User> userList = userReadService.selectFmcode(
				sf.format(now.getTime()), sf.format(nextDay.getTime()));
		for(User u:userList){
			System.out.println(u.getFmcode());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("fmcode", u.getFmcode());
			m.put("timeBy", "create_date");
			m.put("beginDate", sf.format(now.getTime()));
			m.put("endDate", sf.format(nextDay.getTime()));
			u.setLeftCount(userReadService.count(m));
		}
		map.put("userList", userList);
		
		return map;
	}
	
	@RequestMapping("/stamoney")
	@ResponseBody
	public Map<String,Object> stamoney(HttpServletRequest request){
		// 时间格式化
		SimpleDateFormat sf_day = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf_month = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sf_year = new SimpleDateFormat("yyyy");
		
		List<String> dateList = new ArrayList<String>();
		List<Integer> allPayList = new ArrayList<Integer>();
		List<Integer> allNotPayList = new ArrayList<Integer>();
		
		String dateType = request.getParameter("dateType");
		String searchTime = request.getParameter("searchTime");
		
		// 为空的时候初始化时间
		if(org.apache.commons.lang.StringUtils.isEmpty(dateType)){
			dateType="day";
		}
		if(org.apache.commons.lang.StringUtils.isEmpty(searchTime)){
			searchTime=sf_day.format(new Date());
		}
		// 转换时间
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sf_day.parse(searchTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if("month".equals(dateType)){
			Calendar end = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
			end.setTime(calendar.getTime());
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-8);
			start.setTime(calendar.getTime());
			String startT = "";
			String endT = "";
			
			startT=sf_month.format(start.getTime());
			endT=sf_month.format(end.getTime());
			while(!startT.equals(endT)){
				dateList.add(startT);
				allNotPayList.add(ordersReadService.sumOrderPrice(0, startT).intValue());
				allPayList.add(ordersReadService.sumOrderPrice(2, startT).intValue());
				// 月份+1
				start.set(Calendar.MONTH, start.get(Calendar.MONTH)+1);
				startT=sf_month.format(start.getTime());
			}
			
		}else if("year".equals(dateType)){
			Calendar end = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)+1);
			end.setTime(calendar.getTime());
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-8);
			start.setTime(calendar.getTime());
			String startT = "";
			String endT = "";
			
			startT=sf_year.format(start.getTime());
			endT=sf_year.format(end.getTime());
			while(!startT.equals(endT)){
				dateList.add(startT);
				allNotPayList.add(ordersReadService.sumOrderPrice(0, startT).intValue());
				allPayList.add(ordersReadService.sumOrderPrice(2, startT).intValue());
				// 月份+1
				start.set(Calendar.YEAR, start.get(Calendar.YEAR)+1);
				startT=sf_year.format(start.getTime());
			}
		}else{
			Calendar end = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			end.setTime(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-8);
			start.setTime(calendar.getTime());
			String startT = "";
			String endT = "";
			
			startT=sf_day.format(start.getTime());
			endT=sf_day.format(end.getTime());
			while(!startT.equals(endT)){
				dateList.add(startT);
				allNotPayList.add(ordersReadService.sumOrderPrice(0, startT).intValue());
				allPayList.add(ordersReadService.sumOrderPrice(2, startT).intValue());
				// 月份+1
				start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH)+1);
				startT=sf_day.format(start.getTime());
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dateList", dateList);
		map.put("allPayList", allPayList);
		map.put("allNotPayList", allNotPayList);
		
		return map;
	}
	
	/**
	 * 查看
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:orders:info")
	public R info(@PathVariable("id") String id){
		Orders orders = ordersReadService.get(id);
		return R.ok().put("orders", orders);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存订单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:orders:save")
	public R save(@RequestBody Orders orders){
		//ValidatorUtils.validateEntity(user, AddGroup.class);
		ordersWriteService.insert(orders);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改订单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:orders:update")
	public R update(@RequestBody Orders orders){
		//ValidatorUtils.validateEntity(user, UpdateGroup.class);
		String id = orders.getId();
		Orders persistent = ordersReadService.get(id);
		BeanUtils.copyProperties(orders, persistent, 
				new String[] {"id", "createDate", "modifyDate"});
		ordersWriteService.update(persistent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除订单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:orders:delete")
	public R delete(String id){
		ordersWriteService.delete(id);
		return R.ok();
	}
}
