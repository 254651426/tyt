package com.bquan.service.write;

import javax.annotation.Resource;

import com.bquan.dao.write.ProductWriteMapper;
import com.bquan.entity.mysql.Product;

/**
 * 产品 Service层写接口实现
 * @author liuxiaokang
 * @createTime 2016-08-26
 */
public class ProductWriteServiceImpl extends BaseWriteServiceImpl<Product> implements ProductWriteService{

	@Resource
	private ProductWriteMapper productWriteMapper;

	@Override
	public ProductWriteMapper getBaseWriteMapper() {
		return productWriteMapper;
	}
	
}