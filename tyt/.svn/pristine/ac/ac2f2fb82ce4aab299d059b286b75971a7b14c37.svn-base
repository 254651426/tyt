package com.bquan.service.read;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bquan.dao.read.ProductReadMapper;
import com.bquan.entity.mysql.Product;

/**
 * 产品 Service层读接口实现
 * @author liuxiaokang
 * @createTime 2016-08-26
 */
public class ProductReadServiceImpl extends BaseReadServiceImpl<Product> implements ProductReadService{

	@Resource
	private ProductReadMapper productReadMapper;

	@Override
	public ProductReadMapper getBaseReadMapper() {
		return productReadMapper;
	}

	public Product getBySign(String sign) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sign", sign);
		List<Product> productList = productReadMapper.select(map);
		return productList.size()>0?productList.get(0):null;
	}
	
}



