package com.bquan.controller.phone;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bquan.entity.mysql.Commission;
import com.bquan.service.read.CommissionRateReadService;
import com.bquan.service.read.CommissionReadService;
import com.bquan.service.read.CouponReadService;
import com.bquan.service.read.ProductReadService;
import com.bquan.service.read.SendCodeReadService;
import com.bquan.service.read.TipsReadService;
import com.bquan.service.read.UserCouponReadService;
import com.bquan.service.read.UserLineReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.read.UserTokenReadService;
import com.bquan.service.read.WebDomainReadService;
import com.bquan.service.write.CommissionWriteService;
import com.bquan.service.write.SendCodeWriteService;
import com.bquan.service.write.UserCouponWriteService;
import com.bquan.service.write.UserTokenWriteService;
import com.bquan.service.write.UserWriteService;


/**
 * 提取佣金
 * 
 * @author lxk
 * 
 */
@Controller
@RequestMapping(value = "/phone/commission")
public class CommissionController extends BasePhoneController{

    @Autowired
    private UserLineReadService userLineReadService;
    @Autowired
    private ProductReadService productReadService;
    @Autowired
    private UserReadService userReadService;
    @Autowired
    private CouponReadService couponReadService;
    @Autowired
    private UserCouponReadService userCouponReadService;
    @Autowired
    private UserCouponWriteService userCouponWriteService;
    @Autowired
    private CommissionReadService commissionReadService;
    @Autowired
    private UserWriteService userWriteService;
    @Autowired
    private CommissionRateReadService commissionRateReadService;
    @Autowired
    private TipsReadService tipsReadService;
    @Autowired
    private WebDomainReadService webDomainReadService;
    @Autowired
    private SendCodeReadService sendCodeReadService;
    @Autowired
    private UserTokenReadService userTokenReadService;
    @Autowired
    private UserTokenWriteService userTokenWriteService;
    @Autowired
    private SendCodeWriteService sendCodeWriteService;
    @Autowired
    private CommissionWriteService commissionWriteService;
    
    
	
}
