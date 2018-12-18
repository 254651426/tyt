package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.DictionaryWriteMapper;
import com.bquan.entity.mysql.Dictionary;

/**
 * 数据字典 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2017-05-11
 */
public class DictionaryWriteServiceImpl extends BaseWriteServiceImpl<Dictionary> implements DictionaryWriteService{

	@Resource
	private DictionaryWriteMapper dictionaryWriteMapper;

	@Override
	public DictionaryWriteMapper getBaseWriteMapper() {
		return dictionaryWriteMapper;
	}

	@Override
	public void updateFlag() {
		dictionaryWriteMapper.updateFlag();
	}
	
}