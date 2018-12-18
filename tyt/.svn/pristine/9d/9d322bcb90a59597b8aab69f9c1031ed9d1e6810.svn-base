package com.bquan.service.write;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bquan.bean.AlipayConfig;
import com.bquan.bean.WeixinpayConfig;
import com.bquan.dao.write.OrdersWriteMapper;
import com.bquan.entity.mysql.Orders;
import com.bquan.entity.mysql.Product;
import com.bquan.entity.mysql.User;
import com.bquan.service.read.CouponReadService;
import com.bquan.service.read.OrdersReadService;
import com.bquan.service.read.ProductReadService;
import com.bquan.service.read.TbUserReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.util.AlipayUtil;
import com.bquan.util.BigDecimalCalUtil;
import com.bquan.util.DictionaryUtil;
import com.bquan.util.HttpClientUtil;
import com.bquan.util.MD5Util;
import com.bquan.util.RedisUtil;
import com.bquan.util.SpringUtil;
import com.bquan.util.WeixinSignUtil;
import com.bquan.util.XmlUtil;

import net.sf.json.JSONObject;

public class OrdersWriteServiceImpl extends BaseWriteServiceImpl<Orders> implements OrdersWriteService {
 
	@Resource
	private OrdersWriteMapper ordersWriteMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	
	private OrdersReadService ordersReadService=
			(OrdersReadService) SpringUtil.getBean("ordersReadService");
	private ProductReadService productReadService=
			(ProductReadService) SpringUtil.getBean("productReadService");
	private UserReadService userReadService=
			(UserReadService) SpringUtil.getBean("userReadService");
	private UserCouponWriteService userCouponWriteService=
			(UserCouponWriteService) SpringUtil.getBean("userCouponWriteService");
	private CouponReadService couponReadService=
			(CouponReadService) SpringUtil.getBean("couponReadService");
	private UserWriteService userWriteService=
			(UserWriteService) SpringUtil.getBean("userWriteService");

	@Override
	public OrdersWriteMapper getBaseWriteMapper() {
		return ordersWriteMapper;
	}
	
	public Map<String,String> generateWeixinNativeOrder(
			String basePath,String ip,
			String title,String content,String orderId,
			BigDecimal totalFee) {
		Map<String,String> map = new HashMap<String, String>();
		try {
			// 项目基本地址
			//String basePath = RequestUtils.getBasePath(request);
			/* 设置请求微信生成订单需要的参数 */
			WeixinpayConfig weixinOrder = new WeixinpayConfig(
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_appid"), 
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_mch_id"), 
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_key"));
			weixinOrder.setAttach(title);
			weixinOrder.setBody(content);
			weixinOrder.setNonce_str(MD5Util.MD5Encode(String.valueOf(new Random().nextInt()),"UTF-8"));
			weixinOrder.setNotify_url(basePath+"/web/pay/weixinCallBack");
			weixinOrder.setOut_trade_no(orderId);
			weixinOrder.setSpbill_create_ip(ip);
			weixinOrder.setTotal_fee(String.valueOf(BigDecimalCalUtil.getIntValue(totalFee)));
			weixinOrder.setTrade_type("NATIVE");
			/* 请求微信接口 */
			String result = HttpClientUtil.httpPost(
					"https://api.mch.weixin.qq.com/pay/unifiedorder", 
					WeixinSignUtil.getAppTyxdXml(WeixinSignUtil.getAppTyxdSign(weixinOrder)));
			// 将返回的信息转换成json格式
			JSONObject json = XmlUtil.xml2JSON(result);
			System.out.println(json.toString());
			/* 解析返回的数据 */
			String resultStatus = json.getJSONObject("xml").getJSONArray("return_code").getString(0);
			if("SUCCESS".equals(resultStatus)){
				map.put("code_url", 
						json.getJSONObject("xml").getJSONArray("code_url").getString(0));
				map.put("prepay_id", 
						json.getJSONObject("xml").getJSONArray("prepay_id").getString(0));
				return map;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public Map<String, String> generateWeixinAppOrder(String basePath, String ip, String title, String content,
			String orderId, BigDecimal totalFee) {
		Map<String,String> map = new HashMap<String, String>();
		try {
			// 项目基本地址
			//String basePath = RequestUtils.getBasePath(request);
			/* 设置请求微信生成订单需要的参数 */
			WeixinpayConfig weixinOrder = new WeixinpayConfig(
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_appid"), 
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_mch_id"), 
					DictionaryUtil.getDictionaryValue("dictionary_weixin_pay_key"));
			weixinOrder.setAttach(title);
			weixinOrder.setBody(content);
			weixinOrder.setNonce_str(MD5Util.MD5Encode(String.valueOf(new Random().nextInt()),"UTF-8"));
			weixinOrder.setNotify_url(basePath+"/web/pay/weixinCallBack");
			weixinOrder.setOut_trade_no(orderId);
			weixinOrder.setSpbill_create_ip(ip);
			weixinOrder.setTotal_fee(String.valueOf(BigDecimalCalUtil.getIntValue(totalFee)));
			weixinOrder.setTrade_type("NATIVE");
			/* 请求微信接口 */
			String result = HttpClientUtil.httpPost(
					"https://api.mch.weixin.qq.com/pay/unifiedorder", 
					WeixinSignUtil.getAppTyxdXml(WeixinSignUtil.getAppTyxdSign(weixinOrder)));
			// 将返回的信息转换成json格式
			JSONObject json = XmlUtil.xml2JSON(result);
			System.out.println(json.toString());
			/* 解析返回的数据 */
			String resultStatus = json.getJSONObject("xml").getJSONArray("return_code").getString(0);
			if("SUCCESS".equals(resultStatus)){
				System.out.println(json.toString());
				if("SUCCESS".equals(resultStatus)){
					System.out.println(json.toString());
					weixinOrder.setPrepayId(json.getJSONObject("xml").getJSONArray("prepay_id").getString(0));
					// 生成客户端支付需要的参数并返回
					return WeixinSignUtil.getClientPaySign(weixinOrder);
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String generateAlipayOrder(
			String basePath,String ip,
			String title,String content,String orderId,
			BigDecimal totalFee) {
		//String basePath = RequestUtils.getBasePath(request);
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		
		sParaTemp.put("notify_url", basePath+"/web/pay/aliCallBack");
		sParaTemp.put("return_url", basePath+"/web/pay/successReturnPage");
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", ip);
		sParaTemp.put("out_trade_no", orderId);
		sParaTemp.put("subject", title);
		BigDecimal fee = BigDecimalCalUtil.setValueScale(
				BigDecimalCalUtil.div(totalFee, new BigDecimal(100),2),
				2);
		sParaTemp.put("total_fee", fee.toString());
		sParaTemp.put("body", content);
		String html = AlipayUtil.buildRequest(sParaTemp,"get","确认");
		return html;
	}

	/**
	 * 设置用户的VIP时长
	 * @param user	用户
	 * @param pro	购买的产品
	 * @param count	购买的数量
	 */
	private void buyVip(User user,Product pro,int count){
		Integer days = pro.getDay();
		if(days!=null){
			user.setLevel("0");
			if(days==30){
				user.setLevel("1");
			}
			if(days==90){
				user.setLevel("2");
			}
			if(days==180){
				user.setLevel("3");
			}
			if(days==365){
				user.setLevel("4");
			}
			if(days==730){
				user.setLevel("5");
			}
//			if(days==1){
//				user.setLevel("5");
//			}
		}
		Calendar calendar = Calendar.getInstance();
		if(user.getVipEndTime()!=null&&
				user.getVipEndTime().after(new Date())){
			calendar.setTime(user.getVipEndTime());
		}
		int day = pro.getDay();
		for(int i=0;i<count;i++){
			calendar.set(
					Calendar.DAY_OF_MONTH, 
					calendar.get(Calendar.DAY_OF_MONTH)+day);
			// 修改用户VIP时长
			user.setVipEndTime(calendar.getTime());
		}
		
//		try {
//			if(!redisUtil.exists("bannianPassword")){
//				userWriteService.generateShadowsocks();
//			}
//			
//			if("3".equals(user.getLevel())){
//				user.setShadowsocksPassword(redisUtil.get("bannianPassword"));
//				user.setShadowsocksPort(redisUtil.get("bannianPort"));
//			}else if("4".equals(user.getLevel())){
//				user.setShadowsocksPassword(redisUtil.get("yearPassword"));
//				user.setShadowsocksPort(redisUtil.get("yearPort"));
//			}else if("6".equals(user.getLevel())){
//				user.setShadowsocksPassword(redisUtil.get("twoPassword"));
//				user.setShadowsocksPort(redisUtil.get("twoYearPort"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		userWriteService.update(user);
	}
	
	public String dealSuccessOrder(String orderId, String outOrderId,
			PayType payType) {
		
		if(StringUtils.isEmpty(orderId)){
			return "订单号为空！";
		}
		
		Orders order = 
				ordersReadService.getByOrderId(orderId);
		if(order==null){
			return "订单为空！";
		}
			
		if(order.getIsCallBack()!=null&&order.getIsCallBack()){
			return "此数据已经处理过了";
		}
		
		order.setOrderStatus(2);
		order.setPayDate(new Date());
		
		if(payType==PayType.AliPay){
			order.setAliOrderId(outOrderId);
		}else{
			order.setWeixinOrderId(outOrderId);
		}
		// 修改订单回调状态
		order.setIsCallBack(true);
		// 更新订单状态
		update(order);
		
		// 查询用户
		User user = 
				userReadService.getUser(order.getUserId());
		if(user==null){
			return "用户为空！";
		}
		
		// 查询产品
		Product pro = 
				productReadService.getBySign(order.getProductSign());
		if(pro==null){
			return "产品为空！";
		}
		
		/**
		 * 给用户充时间
		 */
		buyVip(user, pro,order.getCount());
		
		/**
		 * 更改此订单使用红包的状态
		 */
		try {
			userCouponWriteService.userCouponByOrderId(order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * 赠送40元红包
		 */
		try {
			userCouponWriteService.generateUserCoupon(
					couponReadService.getDataBySign("zchb40"), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/**
		 * 赠送佣金
		 */
		try {
			userWriteService.setCommission(
					order.getOrderId(),
					user.getUsername(), 
					order.getProductPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "处理完成了";
	}
	
	public String TaijiSuccessOrder(String orderId) {
		if(StringUtils.isEmpty(orderId)){
			return "订单号为空！";
		}
		
		Orders order = 
				ordersReadService.getByOrderId(orderId);
		if(order==null){
			return "订单为空！";
		}
			
		if(order.getOrderStatus().toString().equals("2")){
			return "此数据已经处理过了";
		}
		
		order.setOrderStatus(2);
		order.setPayDate(new Date());
		// 修改订单回调状态
		order.setIsCallBack(true);
		// 更新订单状态
		update(order);
		
		// 查询用户
		User user = 
				userReadService.getUser(order.getUserId());
		if(user==null){
			return "用户为空！";
		}
		
		// 查询产品
		Product pro = 
				productReadService.getBySign(order.getProductSign());
		if(pro==null){
			return "产品为空！";
		}
		
		/**
		 * 给用户充时间
		 */
		buyVip(user, pro,order.getCount());
		return "处理完成了";
	}

}