package com.bquan.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.Dictionary;
import com.bquan.service.read.DictionaryReadService;

public class DictionaryUtil {

	/**
	 * 从redis中获取字典对象
	 * @param key
	 * @return
	 */
	public static Dictionary getDictionary(String key){
		RedisUtil redisUtil = (RedisUtil) SpringUtil.getBean("redisUtil");
		Dictionary dictionary = (Dictionary) redisUtil.getoj(key);
		// redis中没有取到值，则去数据库中取
		if(dictionary==null){
			DictionaryReadService dictionaryReadService = 
					(DictionaryReadService) SpringUtil.getBean("dictionaryReadService");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyText", key);
			List<Dictionary> dictionaryList = 
					dictionaryReadService.getTargetList(map);
			if(dictionaryList==null||dictionaryList.size()==0){
				return null;
			}else{
				// 不为空时，将数据保存到redis中
				dictionary = dictionaryList.get(0);
				redisUtil.setoj(key, dictionary);
			}
		}
		return dictionary;
	}
	
	/**
	 * 获取字典项的value
	 * @param key
	 * @return
	 */
	public static String getDictionaryValue(String key){
		Dictionary dictionary = getDictionary(key);
		try {
			if(dictionary!=null){
				return dictionary.getValueText().trim();
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取字典项的名称
	 * @param key
	 * @return
	 */
	public static String getDictionaryName(String key){
		Dictionary dictionary = getDictionary(key);
		try {
			if(dictionary!=null){
				return dictionary.getNameText().trim();
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取字典项的类型
	 * @param key
	 * @return
	 */
	public static String getDictionaryType(String key){
		Dictionary dictionary = getDictionary(key);
		try {
			if(dictionary!=null){
				return dictionary.getTypeText().trim();
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取字典项的描述
	 * @param key
	 * @return
	 */
	public static String getDictionaryDescribe(String key){
		Dictionary dictionary = getDictionary(key);
		try {
			if(dictionary!=null){
				return dictionary.getDescribeText().trim();
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取字典项是否保存的文件数据
	 * @param key
	 * @return
	 */
	public static Boolean isFile(String key){
		Dictionary dictionary = getDictionary(key);
		if(dictionary!=null){
			return dictionary.getIsFile();
		}else{
			return null;
		}
	}
	
}
