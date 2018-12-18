package com.bquan.util;

import com.alibaba.fastjson.JSON;
import com.bquan.bean.CommonpayConfig;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gettaijipay {

	public String showpay(CommonpayConfig c) {
		HttpTools httpTools = new HttpTools("http://taiji.8n8t.com/api.php/order/create/", "POST");
		Map<String, String> params = new LinkedHashMap<>();
		params.put("merchant_id", c.getMerchant_id()); // 商户号
		params.put("merchant_order_id", c.getMerchant_order_id()); // 商户订单号
		params.put("cash", c.getCash()); // 金额（元）
		params.put("type", c.getType()); // 支付类型 wechat_h5 alipay_h5 wangyin
											// kuaijie
		params.put("return_url", c.getReturn_url()); // 支付完成跳转地址
		params.put("notify_url", c.getNotify_url()); // 支付成功服务器通知地址
		params.put("ip", c.getIp()); // 客户端发起IP地址
		params.put("start_time", c.getStart_time()); // 时间戳
		params.put("random_string", c.getRandom_string()); // 随机字符串（每次不一样）
		// 以上签名用的顺序不能乱
		String key = "a473ade25edd884152febe3ffb1a4a61";
		String sign = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (!StringUtils.isBlank(sign)) {
				sign += "&";
			}
			sign += entry.getKey() + "=" + entry.getValue();

		}
		sign = getMD5(sign + "&key=" + key);
		params.put("sign", sign);
		params.put("version", c.getVersion()); // 版本号
		params.put("trade_type", c.getTrade_type()); // 交易类型
		params.put("app_id", c.getApp_id()); // 应用id
		params.put("goods_name", c.getGoods_name()); // 商品名称
		params.put("business_type", c.getBusiness_type()); // 业务类型：B2C:个人支付，B2B:企业支付：网银接口需要
		params.put("bank_code", c.getBank_code()); // 银行编号：查看银行编号：网银接口需要:比如ICBC
		params.put("format", c.getFormat()); // 返回格式
		params.put("sign_type", c.getSign_type()); // 签名类型
		params.put("extra", "你自定义备注");

		httpTools.body(JSON.toJSONString(params));// 商户自定义备注
		httpTools.sendRequest();
		if (httpTools.getResponseCode() == 200) {
			String body = httpTools.getResponseBody();
			System.out.println(body);
			return body;
		} else {
			System.out.println(String.valueOf(httpTools.getResponseCode()));
		}
		return "";
		// httpTools.getResponseBody()
	}

	public static String getOrderIdByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			result += random.nextInt(10);
		}
		return newDate + result;
	}

	/**
	 * 对字符串md5加密(小写+字母)
	 *
	 * @param str
	 *            传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
