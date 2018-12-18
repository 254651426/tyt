package com.bquan.service.write;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.alibaba.dubbo.config.annotation.Service;
import com.bquan.dao.read.NumbersReadMapper;
import com.bquan.dao.write.NumbersWriteMapper;
import com.bquan.entity.mysql.Numbers;

/**
 * 编号表 Service层写接口实现
 * @author FSY
 * @createTime 2017-05-12
 */
public class NumbersWriteServiceImpl extends BaseWriteServiceImpl<Numbers> implements NumbersWriteService{

	@Resource
	private NumbersWriteMapper numbersWriteMapper;
	
	@Resource
	private NumbersReadMapper numbersReadMapper;
	
	@Override
	public NumbersWriteMapper getBaseWriteMapper() {
		return numbersWriteMapper;
	}
	
	@Override
	public Integer getNumber() {
		Map<String,Object> map = new HashMap<String,Object>();
		Numbers numbers = new Numbers();
		map.put("orderBy", "create_date");
		map.put("orderDesc", "desc");
		List<Numbers> numbersList  = numbersReadMapper.getOne(map);
		if(numbersList!=null&&numbersList.size()>0){
			numbers = numbersList.get(0);
			Integer number = numbers.getNumber()+1;
			numbers.setNumber(number);
			numbersWriteMapper.update(numbers);
			return number;
		}else
		{
			numbers.setNumber(8000000);
			numbersWriteMapper.insert(numbers);
			return 8000000;
		}	
	}
}