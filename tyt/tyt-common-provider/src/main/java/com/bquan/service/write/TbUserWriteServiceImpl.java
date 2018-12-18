package com.bquan.service.write;

import com.bquan.util.RRException;
import com.bquan.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bquan.dao.write.UserWriteDao;
import com.bquan.entity.mysql.UserEntity;
import com.bquan.service.write.TbUserWriteService;

import java.util.List;
import java.util.Map;



public class TbUserWriteServiceImpl implements TbUserWriteService {
	@Autowired
	private UserWriteDao userDao;
	
	@Override
	public void save(UserEntity user){
		user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		userDao.save(user);
	}
	
	@Override
	public void update(UserEntity user){
		userDao.update(user);
	}
	
	@Override
	public void delete(Long userId){
		userDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		userDao.deleteBatch(userIds);
	}

	@Override
	public long login(String mobile, String password) {
		UserEntity user = null;// queryByMobile(mobile);
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("手机号或密码错误");
		}

		return user.getUserId();
	}
}
