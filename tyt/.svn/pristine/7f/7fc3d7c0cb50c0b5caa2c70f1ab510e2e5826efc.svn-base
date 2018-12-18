package com.bquan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 发送短信的工具
 * @author lxk
 *
 */
public class PhoneCodeUtil {
	
	public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }

	public static boolean sendCode(String phone,String content){
		String PostData;
		try {
			PostData = "userid=1180"
					+ "&account=cloudinkw"
					+ "&password=2016tfed"
					+ "&mobile="+phone
					+ "&sendTime="
					+ "&content="+java.net.URLEncoder.encode("【CloudLink】"+content,"utf-8");
			String ret = SMS(PostData, "http://web.28inter.com:8888/sms.aspx?action=send");
			if(ret.contains("<returnstatus>Success</returnstatus>")){
				return true;
			}else{
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
	}
	
}
