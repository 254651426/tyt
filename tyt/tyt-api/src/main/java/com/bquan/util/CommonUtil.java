package com.bquan.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.lang.StringUtils;




import net.sf.json.JSONObject;

/**
 * 工具类 - 公用
 */

public class CommonUtil {

	public static final String WEB_APP_ROOT_KEY = "mrsys.webAppRoot";// WebRoot路径KEY
	public static final String PATH_PREPARED_STATEMENT_UUID = "\\{uuid\\}";// UUID路径占位符
	public static final String PATH_PREPARED_STATEMENT_DATE = "\\{date(\\(\\w+\\))?\\}";// 日期路径占位符

	/**
	 * 获取WebRoot路径
	 * 
	 * @return WebRoot路径
	 */
	public static String getWebRootPath() {
		return System.getProperty(WEB_APP_ROOT_KEY);
	}

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	/**
	 * 获取实际路径
	 * 
	 * @param path
	 *            路径
	 */
	public static String getPreparedStatementPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		StringBuffer uuidStringBuffer = new StringBuffer();
		Matcher uuidMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_UUID)
				.matcher(path);
		while (uuidMatcher.find()) {
			uuidMatcher.appendReplacement(uuidStringBuffer,
					CommonUtil.getUUID());
		}
		uuidMatcher.appendTail(uuidStringBuffer);

		StringBuffer dateStringBuffer = new StringBuffer();
		Matcher dateMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_DATE)
				.matcher(uuidStringBuffer.toString());
		while (dateMatcher.find()) {
			String dateFormate = "yyyyMM";
			Matcher dateFormatMatcher = Pattern.compile("\\(\\w+\\)").matcher(
					dateMatcher.group());
			if (dateFormatMatcher.find()) {
				String dateFormatMatcherGroup = dateFormatMatcher.group();
				dateFormate = dateFormatMatcherGroup.substring(1,
						dateFormatMatcherGroup.length() - 1);
			}
			dateMatcher.appendReplacement(dateStringBuffer,
					new SimpleDateFormat(dateFormate).format(new Date()));
		}
		dateMatcher.appendTail(dateStringBuffer);

		return dateStringBuffer.toString();
	}
	
	/**
	 * 判断是否为数字
	 * 
	 * @return 真假
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 将第一个字母大写
	 * 
	 * @return 字符串
	 */
	public static String upperFirstChar(String str) {
		if (str == null) {
			return null;
		}
		StringBuffer strBuffer = new StringBuffer();
		for (int index = 0; index < str.length(); index++) {
			String c = str.substring(index, index + 1);
			if (index == 0) {
				strBuffer.append(c.toUpperCase());
			} else {
				strBuffer.append(c);
			}
		}
		return strBuffer.toString();
	}
	
	/**
	 * 获取WebService IP
	 * 
	 * @return IP
	 */
/*	public static String getSourceIP(){
		String ip = null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			ip = request.getRemoteAddr()+"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}*/
	
	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] sourceStrArray = ip.split(",");
		if(ip.length()>1){
			ip = sourceStrArray[0];
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	/**
	 * 将字符串位数补足
	 * 
	 * @return 字符串
	 */
	public static String fillStr(int sign, int length){
		String value = String.valueOf(sign);
		int len = value.length();
		for(int index = 0; index < (length -len); index++) {
			value = "0"+value;
		}
		return value;
	}
	
	public static String fillStr(String sign, int length){
		String value = String.valueOf(sign);
		int len = value.length();
		for(int index = 0; index < (length -len); index++) {
			value = "0"+value;
		}
		return value;
	}
	
	/**
	 * 随机生成digCount位数
	 * @param digCount 位数
	 * @return 随机数
	 */
	public static String getRandomNumber(int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
		Random rnd = new Random();
		for (int i = 0; i < digCount; i++)
			sb.append((char) ('0' + rnd.nextInt(10)));
		return sb.toString();
	} 
	
	/**
	 * 检查密码
	 * @return
	 */
	public static int checkPass(String pass){
	 	int ls = 0;
	 	Pattern p = Pattern.compile("([a-zA-Z])+");
		Matcher m = p.matcher(pass);
	 	if(m.find()){
	    	ls++;
	  	}
	 	p = Pattern.compile("([0-9])+");
		m = p.matcher(pass);
	 	if(m.find()){
	    	ls++;  
	 	}
	    return ls;
	 }
	
	/**
	 * 检查密码
	 * @return
	 */
	public static int checkTradePass(String pass){
	 	int ls = 0;
	 	Pattern p = Pattern.compile("([a-zA-Z])+");
		Matcher m = p.matcher(pass);
	 	if(m.find()){
	    	ls++;
	  	}
	 	p = Pattern.compile("([0-9])+");
		m = p.matcher(pass);
	 	if(m.find()){
	    	ls++;  
	 	}
	 	p = Pattern.compile("[^a-zA-Z0-9]+");
		m = p.matcher(pass);
	  	if(m.find()){
	    	ls++;
	    }
	    return ls;
	 }
	
	/**
	 * 获取魔品生成待签名的字符串
	 * @param
	 *     字符串数组
	 * @return
	 *     代签名的字符串
	 */
	public static String getDigitalSignature(String[] strArray) {
		Arrays.sort(strArray, Collections.reverseOrder());
		StringBuilder result = new StringBuilder();
		for(int i =0; i < strArray.length; i++) {
			result.append(strArray[i]);
		}
		return result.toString();
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
/*	public static boolean existAlias(Criteria c, String alias) {
		Iterator itm = ((CriteriaImpl) c).iterateSubcriteria();
		while (itm.hasNext()) {
			Subcriteria sub = (Subcriteria) itm.next();
			if (alias.equals(sub.getAlias())) {
				return true;
			}
		}
		return false;
	}*/

	public static boolean isWechat(HttpServletRequest request) {
		String ua = request.getHeader("user-agent") .toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isAlipay(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent") .toLowerCase();
		if (ua.indexOf("alipayclient") > 0) {// 是支付宝浏览器
			return true;
		}else{
			return false;
		}
	}
	
}