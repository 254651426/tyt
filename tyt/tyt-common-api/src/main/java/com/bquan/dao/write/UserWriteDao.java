package com.bquan.dao.write;

import com.bquan.entity.mysql.UserEntity;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserWriteDao extends BaseWriteDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
