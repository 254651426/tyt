package com.bquan.dao.write;

import java.util.List;

import com.bquan.entity.mysql.WebDomain;

/**
 * 列表设置属性 DAO写接口
 * @author liuxiaokang
 * @createTime 2016-08-20
 */
public interface WebDomainWriteMapper extends BaseWriteMapper<WebDomain> {

	int insertBatch(List<WebDomain> l);
    
}
