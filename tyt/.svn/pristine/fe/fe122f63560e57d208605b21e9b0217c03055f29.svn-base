package com.bquan.util;

import org.springframework.util.StringUtils;

public class StringUtil {

	/**
	 * 将字符串的中间部分转成成***
	 * @param str
	 * @return
	 */
	public static String hideString(String str){
		if(str==null||"".equals(str)){
			return str;
		}
		int len = str.length();
		if(len>0&&len<3){
			return str.substring(0, 1)+"***";
		}
		int a = len/3;
		String b = "";
		for(int i=0;i<a;i++){
			b+="*";
		}
		return str.substring(0,a)+b+str.substring(a*2, str.length());
	}
	
}
