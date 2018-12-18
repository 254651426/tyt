package com.bquan.util;

import java.math.BigDecimal;

public class BigDecimalCalUtil {

	/**
	 * 加法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
		return num1.add(num2);
	}

	/**
	 * 减法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal num1, BigDecimal num2) {
		return num1.subtract(num2);
	}

	/**
	 * 乘法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal num1, BigDecimal num2) {
		return num1.multiply(num2);
	}

	/**
	 * 除法(四舍五入)
	 * 
	 * @param num1
	 * @param num2
	 * @param scale
	 *            精度（精确到几位小数）
	 * @return
	 */
	public static BigDecimal div(BigDecimal num1, BigDecimal num2, int scale) {
		return num1.divide(num2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 比较大小
	 * 
	 * @param num1
	 * @param num2
	 * @return int -1 ：小于，0： 等于，1： 大于。
	 */
	public static int compare(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2);
	}

	/**
	 * 获取整数部分
	 * 
	 * @param num
	 * @return
	 */
	public static int getIntValue(BigDecimal num) {
		return num.intValue();
	}

	/**
	 * 获取小数部分
	 * 
	 * @param num
	 * @return
	 */
	public static BigDecimal getDecimalValue(BigDecimal num) {
		BigDecimal intNum = new BigDecimal(num.intValue());
		BigDecimal decimalNum = sub(num, intNum);
		return decimalNum;
	}

	/**
	 * 获取设置小数位数精度后的值
	 * 
	 * @param num
	 * @param scale
	 *            精度
	 * @return
	 */
	public static BigDecimal setValueScale(BigDecimal num, int scale) {
		return num.setScale(2, BigDecimal.ROUND_DOWN);
	}
}
