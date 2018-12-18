package com.bquan.util.gen;

import org.apache.commons.codec.digest.DigestUtils;

public class Mysina {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	  String ss = "merchant_id=12345678&merchant_order_id=20180711102712165039&cash=1&type=wechat_h5&return_url=www.baidu.com&notify_url=http://notice.pk365.cn/test.php&ip=8.8.8.8&start_time=1531276032&random_string=20180711102712987777&key=9ebc78ed1a14b3579ad067b668b94b27";	
      System.out.println(md5(ss,"9ebc78ed1a14b3579ad067b668b94b27"));
	}

	/**
	 * MD5方法
	 * 
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String md5(String text, String key) throws Exception {
		// 加密后的字符串
		String encodeStr = DigestUtils.md5Hex(text + key);
//		System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
		return encodeStr;
	}
}
