package com.bquan.bean;

import java.io.Serializable;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig  implements Serializable{
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	//public static String partner = "2088221280847301";
	public static String partner = "2088522419084645";
	
	public static String app_id = "2088522419084645";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    //public static String key = "qwil8tc65qmgmmrzrwca906p9beex154";
    public static String key = "ojlf1oyx92rnw5w4yvoov7hv7uep4eu4";
    
    public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKZAOQKwFOXaOBx/bXyiYkkvGZv7viAcmz4FxlHd7RDUC3/15x750HYE3E9Y2xbAfHfWyu8ZoPp2AwL3QelyuPZrDkxOmfj8JIX5DSJJH4xvOXZqeE/ntcyTZzWwqQz82SzLpTFCGpnKl+Em0Emy+3/LgdieZVRGJ6C+gF35pbTlAgMBAAECgYBTAUO1nNaCQGa9i73NeYHbCkIeCB3QCJhV6iovW79uJICtXcgMRiwXkKuyjims/WPnrX1azY/7HQq541usQ9Qmne+/420MAbTYCOs1r/oNqdeY4OWsqWAmvLFtpbiJdzo2KkuvH4iBia7/VNp3C4+apzNqlQ2ppZMLNg9qGcHZ7QJBANd1deqsgyMU35uHFLWtBOJGbgUTT6CTdD4O23iRdxl6ezH6y6M2QmWze7IrKSVYkXT6DPfZ6ClbkjuKvUspnYcCQQDFiHC4PozB93gA+ZTIuJwAqwCBgjfjdB4iSUYAXsGl6MWD9rrPvQTQMLlzyTgCMm6aBG/Z5cnkwhlItPyf5tUzAkEAjBS5On84r2lVzDdx1oCMCVWDGFeq0Jqt298q/+OLjIweZqtJFG8lkhu0U21OThKcV3JvVgFduO3pUD7uvVxsJwJAP4rmh75HVVYgGmAvBmqsdGKN5RtYkyiVUP41MIVK0e4RK6RA7/akB7zEy4RdKgfDIHRw7q1GxvnRXFwsL9v3lQJBAI21LzaSHWC8lWIn+iUS8mDX+NntbWOIixelMHGbkQ9S4fJ1wQog0svgRd45kaNWPasSc7HVHar7AS6BEEVlL4A="; // RSA私匙
    
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://vzbezsgzr9.proxy.qqbrowser.cc/cloudlink/api/dynamicip/test";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://vzbezsgzr9.proxy.qqbrowser.cc/cloudlink/static/index.html";

	// 签名方式
	public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

