package com.bquan.util.gen;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类
 * 
 * @author Rocye
 * @createTime 2013-3-15
 */
public class Utils {
    
    /**
     * 字符转码将ISO8859_1代码转化为GBK
     * @param str
     *           原始采用ISO8859_1编码的字符串
     * @return String
     *           转化为GBK以后的字符串
     */
    public static String iso2gb(String str) {
        if (str != null) {
            byte[] tmpbyte = null;
            try {
                tmpbyte = str.getBytes("ISO8859_1");
            } catch (Exception e) {
                System.out.println("Error: Method: dbconn.iso2gb :"
                        + e.getMessage());
            }
            try {
                str = new String(tmpbyte, "GBK");
            } catch (Exception e) {
                System.out.println("Error: Method: dbconn.gb2iso :"
                        + e.getMessage());
            }
        }
        return str;
    }
    
    /**
     * 字符转码。将GBK代码转化为ISO8859_1
     * 
     * @param str
     *           原始采用GBK编码的字符串
     * @return String
     *           转化为ISO8859_1以后的字符串
     */
    public static String gb2iso(String str) {
        if (str != null) {
            byte[] tmpbyte = null;
            try {
                tmpbyte = str.getBytes("GBK");
            } catch (Exception e) {
                System.out.println("Error: Method: dbconn.gb2iso :"
                        + e.getMessage());
            }
            try {
                str = new String(tmpbyte, "ISO8859_1");
            } catch (Exception e) {
                System.out.println("Error: Method: dbconn.gb2iso :"
                        + e.getMessage());
            }
        }
        return str;
    }
    
    /**
     * 日期数据格式化 返回yyyy-MM-dd格式的日期格式化字符串
     * 
     * @param myDate
     *           原始Date对象日期        
     * @return String
     *           返回yyyy-MM-dd格式的日期格式化字符串
     */
    public static String fotmatDate3(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(myDate);
        return strDate;
    }
    
    public static final char UNDERLINE='_';  
    
    public static String camelToUnderline(String param){  
        if (param==null||"".equals(param.trim())){  
            return "";  
        }  
        int len=param.length();  
        StringBuilder sb=new StringBuilder(len);  
        for (int i = 0; i < len; i++) {  
            char c=param.charAt(i);  
            if (Character.isUpperCase(c)){  
                sb.append(UNDERLINE);  
                sb.append(Character.toLowerCase(c));  
            }else{  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }  
    
    public static String underlineToCamel(String param){  
        if (param==null||"".equals(param.trim())){  
            return "";  
        }  
        int len=param.length();  
        StringBuilder sb=new StringBuilder(len);  
        for (int i = 0; i < len; i++) {  
            char c=param.charAt(i);  
            if (c==UNDERLINE){  
               if (++i<len){  
                   sb.append(Character.toUpperCase(param.charAt(i)));  
               }  
            }else{  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }
    
    public static void main(String[] args) {
		System.out.println(camelToUnderline("userToken"));
		System.out.println(underlineToCamel("user_token"));
	}
}
