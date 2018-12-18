package com.bquan.util;

public class RandomCodeUtil {

	/**
	 * 生成大小写数字的随机码
	 * @param number 随机码位数
	 * @return
	 */
	public static String getRandomCode(int number){
		String a = "0123456789"
					+ "abcdefghijklmnopqrstuvwxyz"
					+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer result = new StringBuffer("");
        for (int i = 0; i < number; i++) { 
            int rand = (int) (Math.random() * a.length()); 
            result.append(a.charAt(rand)); 
        }
        return result.toString();
	}
	
	/**
	 * 获取特定位数的数字随机数
	 * @param number
	 * @return
	 */
	public static String getNumberRandomCode(int number){
		String a = "0123456789";
		StringBuffer result = new StringBuffer("");
        for (int i = 0; i < number; i++) { 
            int rand = (int) (Math.random() * a.length()); 
            result.append(a.charAt(rand)); 
        }
        return result.toString();
	}
}
