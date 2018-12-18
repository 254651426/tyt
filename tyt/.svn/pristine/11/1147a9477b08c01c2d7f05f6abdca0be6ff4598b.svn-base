package com.bquan.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bquan.dao.read.TokenReadDao;
import com.bquan.entity.mysql.TokenEntity;
import com.bquan.service.read.TokenReadService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TokenReadServiceImpl implements TokenReadService {
	@Autowired
	private TokenReadDao tokenDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public TokenEntity queryByUserId(Long userId) {
		return tokenDao.queryByUserId(userId);
	}

	@Override
	public TokenEntity queryByToken(String token) {
		return tokenDao.queryByToken(token);
	}

}
