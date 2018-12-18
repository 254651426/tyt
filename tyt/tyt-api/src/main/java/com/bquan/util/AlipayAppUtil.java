package com.bquan.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.bquan.bean.AlipayConfig;


public class AlipayAppUtil {
	
	public static void main(String[] args) {
		//System.out.println(new AlipayAppUtil().getOrderInfoByAliPay("www.baidu.com", UUID.randomUUID().toString(), 15.9f));
		//System.out.println(URLDecoder.decode("app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D"));
	}

	   /**
	    *支付宝支付
	    * @param orderId 订单编号
	    * @param actualPay 实际支付金额
	    * @return
	    */
	 public static Map<String,String> getOrderInfoByAliPay(
			 String ali_call_back_url,
			 String orderId,
			 BigDecimal totalFee,
			 String subject,
			 String body) {
		 Map<String,String> resultMap = new HashMap<String,String>();
		 BigDecimal fee = BigDecimalCalUtil.setValueScale(
					BigDecimalCalUtil.div(totalFee, new BigDecimal(100),2),
					2);
		 String par1 = "partner=\""+AlipayConfig.partner+"\""
		 		+ "&seller_id=\""+AlipayConfig.seller_id+"\""
		 		+ "&out_trade_no=\""+orderId+"\""
		 		+ "&subject=\""+subject+"\""
		 		+ "&body=\""+body+"\""
		 		+ "&total_fee=\""+fee.toString()+"\""
		 		+ "&notify_url=\""+ali_call_back_url+"\""
		 		+ "&service=\"mobile.securitypay.pay\""
		 		+ "&payment_type=\"1\""
		 		+ "&input_charset=\"utf-8\""
		 		+ "&it_b_pay=\"1m\""
		 		+ "&show_url=\"m.alipay.com\""
		 		+ "&app_id=\""+AlipayConfig.app_id+"\"";
		 
		 resultMap.put("partner", "\""+AlipayConfig.partner+"\"");
		 resultMap.put("seller_id", "\""+AlipayConfig.seller_id+"\"");
		 resultMap.put("out_trade_no", "\""+orderId+"\"");
		 resultMap.put("subject", "\""+subject+"\"");
		 resultMap.put("body", "\""+body+"\"");
		 resultMap.put("total_fee", "\""+fee.toString()+"\"");
		 resultMap.put("notify_url", "\""+ali_call_back_url+"\"");
		 resultMap.put("service", "\"mobile.securitypay.pay\"");
		 resultMap.put("payment_type", "\"1\"");
		 resultMap.put("input_charset", "\"utf-8\"");
		 resultMap.put("it_b_pay", "\"1m\"");
		 resultMap.put("show_url", "\"m.alipay.com\"");
		 resultMap.put("app_id", "\""+AlipayConfig.app_id+"\"");
		 String sign ="";
		 try {
			sign = RSA.sign(par1, AlipayConfig.private_key, "utf-8");
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 par1+="&sign=\""+URLEncoder.encode(sign)+"\""+"&sign_type=\"RSA\"";
		 resultMap.put("sign", "\""+URLEncoder.encode(sign)+"\"");
		 resultMap.put("sign_type", "\"RSA\"");
		 //System.out.println(par1);
		 //return URLEncoder.encode(par1);
		 return resultMap;
	 }
	 
	/**
	 * 支付宝签名
	 * @param array
	 * @return
	 */
	private String signAllString(String [] array){
	   StringBuffer sb = new StringBuffer("");
	   for (int i = 0; i < array.length; i++) {
	      if(i==(array.length-1)){
	         sb.append(array[i]);
	      }else{
	         sb.append(array[i]+"&");
	      }
	   }
	   System.out.println(sb.toString());
	   String sign = "";
	   try {
	      sign = URLEncoder.encode(RSA.sign(sb.toString(), AlipayConfig.private_key, "utf-8"), "utf-8");//private_key私钥
	   } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	   }
	   sb.append("&sign=\""+sign+"\"&");
	   sb.append("sign_type=\"RSA\"");
	   return sb.toString();
	}
}
