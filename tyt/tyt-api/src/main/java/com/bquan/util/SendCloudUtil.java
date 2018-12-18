package com.bquan.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * 邮件发送工具
 * @author Administrator
 *
 */
public class SendCloudUtil {

	public static boolean sendEmail(String title,String content,String toEmail){
		try {
			final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
//		    final String apiUser = "liuxiaokang_test_JhI7LK";
//		    final String apiKey = "doCrhM6e9X7oGaRW";
		    
			final String apiUser = "tianyantong_call";
		    final String apiKey = "47TmoGWlUGBjpTVv";

		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httpost = new HttpPost(url);

		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    // 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
		    params.add(new BasicNameValuePair("api_user", apiUser));
		    params.add(new BasicNameValuePair("api_key", apiKey));
		    params.add(new BasicNameValuePair("from", "cloudlink@cloudlink.im"));
		    params.add(new BasicNameValuePair("fromname", ""));
		    params.add(new BasicNameValuePair("to", toEmail));
		    params.add(new BasicNameValuePair("subject", title));
		    params.add(new BasicNameValuePair("html", content));
		    params.add(new BasicNameValuePair("resp_email_id", "true"));
		    boolean flag = false;
		    httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		    // 请求
		    HttpResponse response = httpclient.execute(httpost);
		    // 处理响应
		    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
		      // 读取xml文档
		      String result = EntityUtils.toString(response.getEntity());
		      JSONObject js = JSONObject.fromObject(result);
		      System.out.println(js.toString());
		      if("error".equals(js.getString("message"))){
		    	// 达到发送上限，切换到VIP邮箱进行发送。
		    	 // flag = MailUtil.sendMail(title, content, toEmail);
		      }else{
		    	  flag = true;
		      }
		    } else {
		    	//flag = MailUtil.sendMail(title, content, toEmail);
		    }
		    httpost.releaseConnection();
		    return flag;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean sendByTemplate(String templateName,String toEmail){
		try {
			final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
//		    final String apiUser = "liuxiaokang_test_JhI7LK";
//		    final String apiKey = "doCrhM6e9X7oGaRW";
		    
			final String apiUser = "tianyantong_send";
		    final String apiKey = "QDdP5PIV04TpIQKk";

		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httpost = new HttpPost(url);

		    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		    // 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
		    params.add(new BasicNameValuePair("api_user", apiUser));
		    params.add(new BasicNameValuePair("api_key", apiKey));
		    params.add(new BasicNameValuePair("from", "cloudlink@cloudlink.im"));
		    //params.add(new BasicNameValuePair("fromName", ""));
		    params.add(new BasicNameValuePair("to", toEmail));
		    params.add(new BasicNameValuePair("template_invoke_name", templateName));
		    //params.add(new BasicNameValuePair("xsmtpapi", ""));
		    
		    boolean flag = false;
		    httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		    // 请求
		    HttpResponse response = httpclient.execute(httpost);
		    // 处理响应
		    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
		      // 读取xml文档
		      String result = EntityUtils.toString(response.getEntity());
		      JSONObject js = JSONObject.fromObject(result);
		      System.out.println(js.toString());
		      if("error".equals(js.getString("message"))){
		    	// 达到发送上限，切换到VIP邮箱进行发送。
		    	
		      }else{
		    	  flag = true;
		      }
		    } else {
		    	
		    }
		    httpost.releaseConnection();
		    return flag;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		sendEmail("您的CloudLink注册验证码", "您的CloudLink注册验证码如下:666666", "787002880@qq.com");
	}
}
