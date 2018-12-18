package com.bquan.api;

import com.bquan.annotation.IgnoreAuth;
import com.bquan.entity.mysql.UserEntity;
import com.bquan.service.read.TbUserReadService;
import com.bquan.service.write.TbUserWriteService;
import com.bquan.util.R;
import com.bquan.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/api")
public class ApiRegisterController {
	
	@Autowired
    private TbUserReadService userReadService;
    @Autowired
    private TbUserWriteService userWriteService;

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping("register")
    public R register(@RequestBody UserEntity user){
        Assert.isBlank(user.getMobile(), "手机号不能为空");
        Assert.isBlank(user.getPassword(), "密码不能为空");

        userWriteService.save(user);

        return R.ok();
    }
}
