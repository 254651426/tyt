package com.bquan.dao.read;

import java.util.List;
import java.util.Map;

import com.bquan.entity.mysql.WebDomain;

/**
 * 列表设置属性 DAO读接口
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public interface WebDomainReadMapper<WebDomain> extends BaseReadMapper<WebDomain>{
	public List<WebDomain> finddeptlist(Map<String,Object> map);
}
