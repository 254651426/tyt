package com.bquan.dao.mongo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.util.DBObjectUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bquan.entity.mongo.PushNews;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
@Repository
public class PushNewsDaoImpl extends BaseMongoDaoImpl<PushNews> implements PushNewsDao{

	public List<PushNews> queryByUserId(String userId) {
		return mongoTemplate.find(
				new Query(new Criteria().where("userId").is(userId)), 
				PushNews.class);
	}

	public PushNews findOneByUserId(String userId,boolean sendFlag) {
		return mongoTemplate.findOne(
				new Query(new Criteria().where("userId").is(userId).and("sendFlag").is(sendFlag)), 
				PushNews.class);
	}
	
	
}
