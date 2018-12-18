package com.bquan.api;

import com.bquan.annotation.IgnoreAuth;
import com.bquan.service.read.TokenReadService;
import com.bquan.service.read.TbUserReadService;
import com.bquan.service.write.TokenWriteService;
import com.bquan.service.write.TbUserWriteService;
import com.bquan.util.R;
import com.bquan.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * API登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
	
    @Autowired
    private TbUserReadService userReadService;
    @Autowired
    private TbUserWriteService userWriteService;
    @Autowired
    private TokenReadService tokenReadService;
    @Autowired
    private TokenWriteService tokenWriteService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    public R login(String mobile, String password){
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userWriteService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenWriteService.createToken(userId);

        return R.ok(map);
    }

}
