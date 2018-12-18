package com.bquan.service.mongo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.alibaba.dubbo.config.annotation.Service;
import com.bquan.dao.mongo.PushNewsDao;
import com.bquan.entity.mongo.PushNews;

/**
 * 推送消息
 * @author liuxiaokang
 * @createTime 2016-09-05
 */
public class PushNewsServiceImpl extends BaseMongodbServiceImpl<PushNews> implements PushNewsService{

	@Autowired
	private PushNewsDao pushNewsDao;

	@Override
	public PushNewsDao getBaseMongodbDao() {
		return pushNewsDao;
	}

	public List<PushNews> queryByUserId(String userId) {
		return pushNewsDao.queryByUserId(userId);
	}

	public PushNews findOneByUserId(String userId,boolean sendFlag) {
		return pushNewsDao.findOneByUserId(userId,sendFlag);
	}
	
}



