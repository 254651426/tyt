package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.CloudlinkArticleWriteMapper;
import com.bquan.entity.mysql.CloudlinkArticle;

/**
 * 文章 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-21
 */
public class CloudlinkArticleWriteServiceImpl extends BaseWriteServiceImpl<CloudlinkArticle> implements CloudlinkArticleWriteService{

	@Resource
	private CloudlinkArticleWriteMapper cloudlinkArticleWriteMapper;

	@Override
	public CloudlinkArticleWriteMapper getBaseWriteMapper() {
		return cloudlinkArticleWriteMapper;
	}
	
}