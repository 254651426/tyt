package com.bquan.service.read;

import com.bquan.entity.mysql.Dictionary;

/**
 * 数据字典 Service读数据接口
 * @author liuxiaokang
 * @createTime 2017-05-11
 */
public interface DictionaryReadService extends BaseReadService<Dictionary>{

	/**
	 * 将数据字典的数据加载到内存中
	 * @return
	 */
	public Boolean loadDateToRedis();
	
	/**
	 * 判断标识是否存在
	 * @param oldKey
	 * @param newKey
	 * @return
	 */
	public boolean isUniqueBySign(String oldKey,String newKey);
}



