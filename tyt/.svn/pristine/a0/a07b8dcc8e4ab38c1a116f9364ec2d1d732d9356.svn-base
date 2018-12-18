package com.bquan.service.mongo;

import java.util.List;

import com.bquan.entity.mongo.PushNews;

/**
 * 推送消息
 * @author liuxiaokang
 * @createTime 2016-09-05
 */
public interface PushNewsService extends BaseMongodbService<PushNews>{

	/**
	 * 通过userid查询推送消息
	 * @param userId
	 * @return
	 */
	public List<PushNews> queryByUserId(String userId);
	
	/**
	 * 通过userid查询一条推送消息
	 * @param userId
	 * @return
	 */
	public PushNews findOneByUserId(String userId,boolean sendFlag);
}



