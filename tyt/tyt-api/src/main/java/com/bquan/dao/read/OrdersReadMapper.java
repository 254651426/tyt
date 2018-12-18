package com.bquan.dao.read;

import java.math.BigDecimal;
import java.util.Map;

import com.bquan.entity.mysql.Orders;

public interface OrdersReadMapper extends BaseReadMapper<Orders>{

	public BigDecimal sumRealCommissionPrice(Map<String,Object> map);
}