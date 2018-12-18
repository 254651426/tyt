package com.bquan.util;

public class UrlUtil {

	/**
	 * 通过相对路径获取url路径
	 * @param relativelyPath
	 * @return
	 */
	public static String getImgPath(String relativelyPath){
		return "http://10.200.52.137:8080/douqu_manager"+relativelyPath;
	}
}
