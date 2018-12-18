package com.bquan.service.write;

import com.bquan.entity.mysql.Dictionary;

/**
 * 数据字典 Service写数据接口
 * @author liuxiaokang
 * @createTime 2017-05-11
 */
public interface DictionaryWriteService extends BaseWriteService<Dictionary>{

	/**
	 * 将所有要更新的状态改为false
	 */
	public void updateFlag();
}