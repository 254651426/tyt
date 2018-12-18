package com.bquan.service.mongo;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bquan.bean.Pager;
import com.bquan.dao.mongo.BaseMongodbDao;
import com.bquan.service.mongo.BaseMongodbService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public abstract class BaseMongodbServiceImpl<T> implements BaseMongodbService<T>{
	
	
	public abstract BaseMongodbDao<T> getBaseMongodbDao();

	public void delete(T object) {
		getBaseMongodbDao().delete(object);
	}

	public List<T> findAll() {
		return getBaseMongodbDao().findAll();
	}

	public T getById(Serializable id) {
		return getBaseMongodbDao().getById(id);
	}

	public List<T> getByIds(Serializable[] ids) {
		return getBaseMongodbDao().getByIds(ids);
	}

	public void deleteByIds(Serializable[] ids) {
		getBaseMongodbDao().deleteByIds(ids);
	}

	public void save(T entity) {
		getBaseMongodbDao().save(entity);
	}

	public void update(Query query, Update update) {
		getBaseMongodbDao().update(query, update);
	}
	
	public void updateById(T entity) {
		getBaseMongodbDao().updateById(entity);
	}

	public List<T> getPageData(int pageNum, int pageSize) {
		return getBaseMongodbDao().getPageData(pageNum, pageSize);
	}
	
	public Pager findPager(Pager pager){
		return getBaseMongodbDao().findPager(pager);
	}

	public String saveFile(InputStream in, String filename, String contentType, DBObject metadata) throws Exception {
		return getBaseMongodbDao().saveFile(in, filename, contentType, metadata);
	}

	public GridFSDBFile findOneFile(String fileId) {
		return getBaseMongodbDao().findOneFile(fileId);
	}

	public List<GridFSDBFile> findAllFile(Query query) {
		return getBaseMongodbDao().findAllFile(query);
	}

	public void deleteFileByFileId(String fileId) {
		getBaseMongodbDao().deleteFileByFileId(fileId);
	}

	public void deleteByQuery(Query query) {
		getBaseMongodbDao().deleteByQuery(query);
	}
	
}
