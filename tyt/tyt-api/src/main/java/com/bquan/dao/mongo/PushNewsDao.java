package com.bquan.dao.mongo;

import java.io.IOException;
import java.util.List;

import com.bquan.entity.mongo.PushNews;


public interface PushNewsDao extends BaseMongodbDao<PushNews>{
	
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
