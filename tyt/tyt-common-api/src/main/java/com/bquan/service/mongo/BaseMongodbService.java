package com.bquan.service.mongo;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bquan.bean.Pager;
import com.github.pagehelper.PageInfo;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface BaseMongodbService<T> {

	public abstract void delete(T object);

	public abstract List<T> findAll();

	public abstract T getById(Serializable id);

	public abstract List<T> getByIds(Serializable[] ids);

	public abstract void deleteByIds(Serializable[] ids);

	public abstract void save(T entity);

	public abstract void update(Query query, Update update);
	
	public abstract void updateById(T entity);

	public abstract List<T> getPageData(int pageNum, int pageSize);
	
	public Pager findPager(Pager pager);
	
	public abstract String saveFile(InputStream in,String filename,String contentType,DBObject metadata) throws Exception;
	
	public abstract GridFSDBFile findOneFile(String fileId);
	
	public List<GridFSDBFile> findAllFile(Query query);
	
	public void deleteFileByFileId(String fileId);
	
	public void deleteByQuery(Query query);
	
}
