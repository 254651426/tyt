package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.bquan.dao.read.DictionaryReadMapper;
import com.bquan.entity.mysql.Dictionary;
import com.bquan.util.RedisUtil;

/**
 * 数据字典 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2017-05-11
 */
public class DictionaryReadServiceImpl extends BaseReadServiceImpl<Dictionary> implements DictionaryReadService{

	@Resource
	private DictionaryReadMapper dictionaryReadMapper;
	@Resource
	private RedisUtil redisUtil;

	@Override
	public DictionaryReadMapper getBaseReadMapper() {
		return dictionaryReadMapper;
	}

	@Override
	public Boolean loadDateToRedis() {
		List<Dictionary> dictionaryList = 
				dictionaryReadMapper.select(new HashMap<String,Object>());
		for(Dictionary dictionary:dictionaryList){
			redisUtil.setoj(dictionary.getKeyText(), dictionary);
		}
		return true;
	}

	@Override
	public boolean isUniqueBySign(String oldKey, String newKey) {
		if (StringUtils.equalsIgnoreCase(oldKey, newKey)) {
			return true;
		} else {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyText", newKey);
			List<Dictionary> list = dictionaryReadMapper.select(map);
			if (list!=null&&list.size()>0) {
				return false;
			} else {
				return true;
			}
		}
	}
	
}



