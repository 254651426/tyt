package com.bquan.service.read;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.dao.read.OrdersReadMapper;
import com.bquan.entity.mysql.Orders;


public class OrdersReadServiceImpl extends BaseReadServiceImpl<Orders> implements OrdersReadService {
 
	@Resource
	private OrdersReadMapper ordersReadMapper;
	
	@Override
	public OrdersReadMapper getBaseReadMapper() {
		return ordersReadMapper;
	}

	public Orders get(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		List<Orders> ordersList = ordersReadMapper.select(map);
		return ordersList.size()>0?ordersList.get(0):null;
	}

	public Orders getByOrderId(String orderId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		List<Orders> ordersList = ordersReadMapper.select(map);
		if(ordersList.size()>0){
			return ordersList.get(0);
		}else{
			return null;
		}
	}

	public BigDecimal sumOrderPrice(int orderStatus, String time) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderStatus", orderStatus);
		map.put("searchBy", "orders.create_date");
		map.put("keyword", time);
		map.put("sumItem", "orders.product_price");
		return ordersReadMapper.sum(map);
	}

	public BigDecimal sumRealCommissionPrice(String fmcode, String beginDate, String endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fmcode", fmcode);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return ordersReadMapper.sumRealCommissionPrice(map);
	}

}