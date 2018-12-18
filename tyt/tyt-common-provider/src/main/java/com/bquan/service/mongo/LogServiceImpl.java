package com.bquan.service.mongo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

import com.bquan.dao.mongo.LogDao;
import com.bquan.entity.mongo.Log;
import com.bquan.service.mongo.LogService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 日志
 * @author liuxiaokang
 * @createTime 2016-09-05
 */

public class LogServiceImpl extends BaseMongodbServiceImpl<Log> implements LogService{

	@Autowired
	private LogDao logDao;

	@Override
	public LogDao getBaseMongodbDao() {
		return logDao;
	}

}



