package com.bquan.service.write;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bquan.entity.mysql.Orders;

public interface OrdersWriteService extends BaseWriteService<Orders>{

	public enum PayType{
		WeixinPay,AliPay
	}
	
	/**
	 * 生成微信订单,返回的是生成的支付二维码图片在服务器上的地址
	 * @param request
	 * @param title		订单描述标题
	 * @param content	订单描述内容
	 * @param orderId	订单号
	 * @param totalFee	订单费用(单位是分)
	 * @return
	 */
	public Map<String,String> generateWeixinNativeOrder(String basePath,String ip,String title,String content,String orderId,BigDecimal totalFee);
	
	/**
	 * 生成微信app订单
	 * @param request
	 * @param title		订单描述标题
	 * @param content	订单描述内容
	 * @param orderId	订单号
	 * @param totalFee	订单费用(单位是分)
	 * @return
	 */
	public Map<String,String> generateWeixinAppOrder(String basePath,String ip,String title,String content,String orderId,BigDecimal totalFee);
	
	/**
	 * 生成支付宝订单，返回的是支付宝的支付页面
	 * @param request
	 * @param title
	 * @param content
	 * @param orderId
	 * @param totalFee	订单费用(单位是分)
	 * @return
	 */
	public String generateAlipayOrder(String basePath,String ip,String title,String content,String orderId,BigDecimal totalFee);
	
	/**
	 * 支付成功的订单的处理
	 * @param orderId
	 * @param outOrderId
	 * @param payType
	 * @return
	 */
	public String dealSuccessOrder(String orderId, String outOrderId,
			PayType payType) ;
	
	/**
	 * 太极支付成功的订单的处理
	 * @param orderId
	 * @param payType
	 * @return
	 */
	public String TaijiSuccessOrder(String orderId) ;
}
