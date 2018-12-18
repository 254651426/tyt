package com.bquan.dao.mongo;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.bquan.bean.Pager;
import com.bquan.bean.Pager.Order;
import com.bquan.dao.mongo.BaseMongodbDao;
import com.bquan.util.JsonUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public abstract class BaseMongoDaoImpl<T> implements BaseMongodbDao<T> {
	
	@Autowired
    protected MongoTemplate mongoTemplate;
	@Autowired
	protected GridFsTemplate gridFsTemplate;
	
	private Class<T> clazz = null;
    //通过反射技术得到T的真实类型
    public BaseMongoDaoImpl(){
    	//获得当前new的对象的父类的类型
    	ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
    	//获得第一个参数类型的真实类型
    	this.clazz = (Class<T>)pt.getActualTypeArguments()[0];
    }
    
	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#delete(T)
	 */
	public void delete(T object) {
		if(object!= null){
			mongoTemplate.remove(object);
		}
	}
	public void delete(Serializable id){
		mongoTemplate.remove(getById(id));
	}
	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#findAll()
	 */
	public List<T> findAll() {
		return mongoTemplate.findAll(clazz);
	}

	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#getById(java.io.Serializable)
	 */
	public T getById(Serializable id) {
		if(id==null){
			return null;
		}else{
			return (T)mongoTemplate.findById(id,clazz);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#getByIds(java.io.Serializable[])
	 */
	public List<T> getByIds(Serializable[] ids) {
		if(ids.length==0||ids==null){
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < ids.length; i++) {
			list.add(getById(ids[i]));
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#deleteByIds(java.io.Serializable[])
	 */
	public void deleteByIds(Serializable[] ids){
		for(int i=0;i<ids.length;i++){
			delete(ids[i]);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#save(T)
	 */
	public void save(T entity) {
		mongoTemplate.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#update(org.springframework.data.mongodb.core.query.Query, org.springframework.data.mongodb.core.query.Update)
	 */
	public void update(Query query,Update update) {
		mongoTemplate.updateMulti(query, update, clazz);
	}
	
	public void updateById(T entity) {
		// TODO Auto-generated method stub
		String jsonStr = JsonUtil.toJson(entity);
		Map<String,Object> map = JsonUtil.parseJSON2Map(jsonStr);
		String id = "";
		Update update = new Update();
		for(String key:map.keySet()){
			if("id".equals(key)){
				id=(String) map.get(key);
			}else{
				// 不更新创建时间
				if(!"createDate".equals(key)){
					update.set(key, map.get(key));
				}
			}
		}
		update.set("modifyDate", new Date());
		if(StringUtils.isNotEmpty(id)){
			mongoTemplate.updateMulti(
					new Query(new Criteria().where("id").is(id)),update, entity.getClass());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lifeng.fastssmr.base.impl.BaseDao1#getPageData(int, int)
	 */
	public List<T> getPageData(int pageNum,int pageSize){
		int begin = (pageNum-1)*pageSize;
		int end = pageNum*pageSize;
		return mongoTemplate.find(new Query().skip(begin).limit(end), clazz);
	}
	
	public Pager findPager(Pager pager){
		
		BasicDBObject basicDBObject= new BasicDBObject();
		if(StringUtils.isNotEmpty(pager.getSearchBy())
				&&StringUtils.isNotEmpty(pager.getKeyword())){
			basicDBObject.put(pager.getSearchBy(), new BasicDBObject("$regex", pager.getKeyword()).append("$options", "i"));
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_1())
				&&StringUtils.isNotEmpty(pager.getValueExtra_1())){
			basicDBObject.put(pager.getSearchByExtra_1(), new BasicDBObject("$regex", pager.getValueExtra_1()).append("$options", "i"));
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_2())
				&&StringUtils.isNotEmpty(pager.getValueExtra_2())){
			basicDBObject.put(pager.getSearchByExtra_2(), new BasicDBObject("$regex", pager.getValueExtra_2()).append("$options", "i"));
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_3())
				&&StringUtils.isNotEmpty(pager.getValueExtra_3())){
			basicDBObject.put(pager.getSearchByExtra_3(), new BasicDBObject("$regex", pager.getValueExtra_3()).append("$options", "i"));
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_4())
				&&StringUtils.isNotEmpty(pager.getValueExtra_4())){
			basicDBObject.put(pager.getSearchByExtra_4(), new BasicDBObject("$regex", pager.getValueExtra_4()).append("$options", "i"));
		}
		if(StringUtils.isNotEmpty(pager.getSearchByExtra_5())
				&&StringUtils.isNotEmpty(pager.getValueExtra_5())){
			basicDBObject.put(pager.getSearchByExtra_5(), new BasicDBObject("$regex", pager.getValueExtra_5()).append("$options", "i"));
		}
		
		if(StringUtils.isNotEmpty(pager.getTimeBy())){
//			if(pager.getBeginDate()!=null&&pager.getEndDate()==null){
//				basicDBObject.put(
//						pager.getTimeBy(), 
//						new BasicDBObject("$gt", pager.getBeginDate())
//						);
//			}
//			if(pager.getBeginDate()==null&&pager.getEndDate()!=null){
//				basicDBObject.put(
//						pager.getTimeBy(), 
//						new BasicDBObject("$lt", pager.getEndDate())
//						);
//			}
			if(pager.getBeginDate()!=null&&pager.getEndDate()!=null){
				basicDBObject.put(
						pager.getTimeBy(), 
						new BasicDBObject("$gte", pager.getBeginDate())
						.append("$lte", pager.getEndDate()));
			}
		}
		
		BasicQuery query = new BasicQuery(basicDBObject);
		
		// 查询总数
		pager.setTotalCount((int)mongoTemplate.count(query, clazz));
		
		// 分页设置
		int begin = (pager.getPageNumber()-1)*pager.getPageSize();
		int end = (pager.getPageNumber())*pager.getPageSize();
		query.skip(begin).limit(end);
		
		// 排序
		if(StringUtils.isNotEmpty(pager.getOrderBy())){
			if(pager.getOrder()!=null){
				if(pager.getOrder()==Order.asc){
					query.with(new Sort(Direction.ASC, pager.getOrderBy()));
				}else{
					query.with(new Sort(Direction.DESC, pager.getOrderBy()));
				}
			}else{
				query.with(new Sort(Direction.DESC, pager.getOrderBy()));
			}
		}else{
			query.with(new Sort(Direction.DESC, "createDate"));
		}
		
		pager.setResult(mongoTemplate.find(query, clazz));
		
		return pager;
	}
	
	/**
	 * 保存文件，并返回文件id
	 * @param in
	 * @param filename
	 * @param contentType
	 * @param metadata
	 * @return
	 * @throws Exception
	 */
	public String saveFile(InputStream in,String filename,String contentType,DBObject metadata) throws Exception{
		try {
			DB db = mongoTemplate.getDb();
			return gridFsTemplate.store(in, filename, contentType, metadata).getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存文件异常",e);
		}
		
	}
	/**
	 * 通过fileId 获取文件,返回唯一
	 * @param fileId
	 * @return
	 */
	public GridFSDBFile findOneFile(String fileId){
		GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
		return gridFSDBFile;
	}
	/**
	 * 根据条件查询文件
	 * @param query
	 * @return
	 */
	public List<GridFSDBFile> findAllFile(Query query){
		List<GridFSDBFile> gridFSDBFile = gridFsTemplate.find(query);
		return gridFSDBFile;
	}
	
	/**
	 * 通过文件id删除
	 * @param fileId
	 */
	public void deleteFileByFileId(String fileId){
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(fileId)));
	}
	/**
	 * 通过条件删除
	 * @param query
	 */
	public void deleteByQuery(Query query){
		gridFsTemplate.delete(query);
	}
}
