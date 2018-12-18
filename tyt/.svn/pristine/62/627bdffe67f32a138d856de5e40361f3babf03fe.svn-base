package com.bquan.service.read;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.Orders;


public interface OrdersReadService extends BaseReadService<Orders>{

	/**
	 * 通过数据库中的order_id来取数据
	 * @param orderId
	 * @return
	 */
	public Orders getByOrderId(String orderId);
	
	/**
	 * 通过订单状态和时间统计金额
	 * @param orderStatus
	 * @param time
	 * @return
	 */
	public BigDecimal sumOrderPrice(int orderStatus,String time);
	
	/**
	 * 统计渠道的真实金额
	 * @param fmcode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public BigDecimal sumRealCommissionPrice(String fmcode,String beginDate,String endDate);
}
