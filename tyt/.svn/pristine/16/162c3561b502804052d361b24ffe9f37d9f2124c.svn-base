package com.bquan.controller.plug;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.config.annotation.Reference;
import com.bquan.entity.mysql.Orders;
import com.bquan.entity.mysql.Product;
import com.bquan.entity.mysql.User;
import com.bquan.entity.mysql.UserCoupon;
import com.bquan.service.read.CouponReadService;
import com.bquan.service.read.OrdersReadService;
import com.bquan.service.read.ProductReadService;
import com.bquan.service.read.UserCouponReadService;
import com.bquan.service.read.UserReadService;
import com.bquan.service.write.OrdersWriteService;
import com.bquan.service.write.OrdersWriteService.PayType;
import com.bquan.service.write.UserCouponWriteService;
import com.bquan.service.write.UserWriteService;
import com.bquan.util.AlipayUtil;
import com.bquan.util.BigDecimalCalUtil;
import com.bquan.util.HttpClientUtil;
import com.bquan.util.JsonUtil;
import com.bquan.util.MD5Util;
import com.bquan.util.RequestUtils;
import com.bquan.util.XmlUtil;
import com.google.zxing.WriterException;

/**
 * 支付action
 * 
 * @author hedaokun
 * 
 */
@Controller
@RequestMapping(value = "/plug/pay")
public class PayController{
	
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
    private UserWriteService userWriteService;
	@Autowired
    private OrdersReadService ordersReadService;
	@Autowired
    private OrdersWriteService ordersWriteService;
    
    private void outPut(HttpServletResponse response,String data){
    	try {
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/html;charset=UTF-8");
    		OutputStream os = response.getOutputStream();
    		BufferedWriter bufWriter = new BufferedWriter(
    				new OutputStreamWriter(os));
    		bufWriter.write(data);
    		bufWriter.flush();
    		bufWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
     
	/**
	 * 选择支付方式(微信还是支付宝)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/choosePay")
    @ResponseBody
    public ModelAndView choosePay(HttpServletRequest request, 
    		HttpServletResponse response){
		String userName = request.getParameter("userName");
		String productSign = request.getParameter("productSign");
		String count = request.getParameter("count");
		
		// count参数异常时默认购买一个
		try {
			int cnt = Integer.parseInt(count);
			if(cnt<=0){
				count="1";
			}
		} catch (Exception e) {
			count="1";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userName", userName);
		if(StringUtils.isEmpty(userName)||
				StringUtils.isEmpty(productSign)){
			mv.addObject("errInfo", "参数错误");
			mv.setViewName("/layout/error2");
			return mv;
		}
		if("$userName".equals(userName)){
			mv.addObject("errInfo", "支付出现异常，请重新下单");
			mv.setViewName("/layout/error2");
			return mv;
		}
		
		Product pro = productReadService.getBySign(productSign);
		if(pro==null){
			mv.addObject("errInfo", "产品不存在");
			mv.setViewName("/layout/error2");
			return mv;
		}
		User u = userReadService.getUser(userName);
		if(u==null){
			mv.addObject("errInfo", "账户不存在");
			mv.setViewName("/layout/error2");
			return mv;
		}
		
		// 查询用户可使用的优惠券
		BigDecimal price = BigDecimalCalUtil.mul(
				pro.getPrice(), new BigDecimal(Integer.parseInt(count)));
		UserCoupon userCoupon = null;
		userCoupon=userCouponWriteService
				.getAvailableUserCoupon(price, u);
		
		mv.addObject("price", price);
		mv.addObject("userName", userName);
		mv.addObject("productSign", productSign);
		mv.addObject("count", count);
		mv.addObject("userCoupon", userCoupon);
		if(userCoupon==null){
			mv.addObject("payPrice", price);
		}else{
			mv.addObject("payPrice", 
					BigDecimalCalUtil.sub(price, userCoupon.getPrice()));
		}
		mv.setViewName("/mobile/choose_pay");
	    return mv;
		
    }
    
	/**
	 * 跳到扫码支付界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/goPay")
    @ResponseBody
	public ModelAndView goPay(
			HttpServletRequest request, 
    		HttpServletResponse response){
		
		/**
		 * 获取参数
		 */
		String payWay = request.getParameter("payWay");// 获取支付方式
		String userName = request.getParameter("userName");// 获取充值账户
		String productSign = request.getParameter("productSign");// 获取产品标识
		String count = request.getParameter("count");// 购买数量
		
		ModelAndView mv = new ModelAndView();
		String OrderId = MD5Util.MD5(UUID.randomUUID().toString());//生成订单号

		/**
		 * 获取产品信息和参数校验
		 */
		Product pro = productReadService.getBySign(productSign);
		if("$userName".equals(userName)){
			mv.addObject("errInfo", "支付出现异常，请重新下单购买！");
			mv.setViewName("/layout/error2");
			return mv;
		}
		// 验证产品标识
		if(pro==null){
			mv.addObject("errorInfo","产品标识错误！");
			mv.setViewName("/layout/error2");
			return mv;
		}
		
		/**
		 * 创建订单
		 */
		Orders orders = new Orders();
		orders.setUserId(userName);
		orders.setProductSign(productSign);
		orders.setProductName(pro.getName());
		orders.setCount(Integer.parseInt(count));
		orders.setCreateDate(new Date());
		orders.setIsCallBack(false);
		
		// 计算支付需要的总价钱
		BigDecimal payPri = BigDecimalCalUtil.mul(
				BigDecimalCalUtil.mul(pro.getPrice(), new BigDecimal(100)), 
				new BigDecimal(count)); 
		User u = userReadService.getUser(userName);
		if(u==null){
			mv.addObject("errorInfo","账户不存在！");
			mv.setViewName("/layout/error2");
			return mv;
		}
		UserCoupon userCoupon = null;
		// 获取此订单可使用的优惠券
		userCoupon = userCouponWriteService.getAvailableUserCoupon(
				BigDecimalCalUtil.mul(pro.getPrice(), new BigDecimal(count)), 
				u);
		if(userCoupon!=null){
			// 计算优惠后的价格
			payPri = BigDecimalCalUtil.sub(
					payPri, 
					BigDecimalCalUtil.mul(userCoupon.getPrice(),new BigDecimal(100)));
			orders.setUserCouponId(userCoupon.getId());
		}
		
		orders.setProductPrice(BigDecimalCalUtil.div(payPri, new BigDecimal(100), 2));// 设置订单价钱
		
		String title = "影盾订单";
		String content = "影盾订单";
		
		if("weixin".equals(payWay)){
			/**
			 * 使用微信进行支付
			 */
			Map<String,String> map = ordersWriteService.generateWeixinAppOrder(
					RequestUtils.getBasePath(request),
					RequestUtils.getIp(request), 
					title, content,OrderId, payPri);
			System.out.println(JsonUtil.toJson(map));
		}else if("zhifubao".equals(payWay)){
			/**
			 * 使用支付宝进行支付
			 */
			String html = ordersWriteService.generateAlipayOrder(
					RequestUtils.getBasePath(request),
					RequestUtils.getIp(request), 
					title, content, OrderId, payPri);
			try {
				/**
				 * 保存订单
				 */
				orders.setPayCreateDate(new Date());
				orders.setOrderStatus(0);
				orders.setOrderId(OrderId);
				ordersWriteService.insert(orders);
				response.getWriter().println(html);
			} catch (IOException e) {
				mv.addObject("errInfo", "请求异常");
				mv.setViewName("/layout/error");
			}
			return null;
		}else{
			mv.addObject("errInfo", "参数错误");
			mv.setViewName("/layout/error");
		}
	    return mv;
	}
	
	/**
	 * 微信支付的回调
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/weixinCallBack")
    @ResponseBody
	public String weixinCallBack(HttpServletRequest request, 
    		HttpServletResponse response){
		try {
			System.out.println("支付成功的返回了");
			/* 接受返回的结果信息 */
			String result = HttpClientUtil.getBodyString(request.getReader());
			System.out.println(result);
			JSONObject resultJson = XmlUtil.xml2JSON(result);
			/* 返回状态码 */
			if("SUCCESS".equals(resultJson.getJSONObject("xml").getJSONArray("return_code").getString(0))){
				String outTradeNo = resultJson.getJSONObject("xml").getJSONArray("out_trade_no").getString(0);//订单号
				String transactionId = resultJson.getJSONObject("xml").getJSONArray("transaction_id").getString(0);//微信订单号
				System.out.println("成功了：单号"+transactionId);
				/* 返回结果码 */
				if("SUCCESS".equals(resultJson.getJSONObject("xml").getJSONArray("result_code").getString(0))){
					System.out.println("订单号为"+outTradeNo);
					System.out.println("微信订单号为"+transactionId);
					ordersWriteService.dealSuccessOrder(
							outTradeNo, transactionId, PayType.WeixinPay);
				}else{
					/* 支付失败设置订单状态和失败原因 */
					String errCode = resultJson.getJSONObject("xml").getJSONArray("err_code").getString(0);
					String errCodeDes = resultJson.getJSONObject("xml").getJSONArray("err_code_des").getString(0);
					System.out.println("支付失败,errcode="+errCode+",errCodeDes="+errCodeDes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* 向微信返回信息，告之已成功回调 */
		String responseMsg = 
				"<xml>"
				  +"<return_code><![CDATA[SUCCESS]]></return_code>"
				  +"<return_msg><![CDATA[OK]]></return_msg>"
				+"</xml>";
		outPut(response,responseMsg);
		return null;
	}
	
	/**
	 * 支付宝支付服务器异步回调地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/aliCallBack")
    @ResponseBody
	public String aliCallBack(HttpServletRequest request, HttpServletResponse response){
		try {
			/**
			 * 获取支付宝POST过来反馈信息
			 */
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			/**
			 * 获取支付宝的通知返回参数
			 */
			String out_trade_no = //商户订单号
					new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			String trade_no = //支付宝交易号
					new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			String trade_status = //交易状态
					new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			/**
			 * 验证消息是否是支付宝发出的合法消息
			 */
			if(AlipayUtil.verify(params)){
				
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序
						
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")){
					
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序
					ordersWriteService.dealSuccessOrder(
							out_trade_no, trade_no, PayType.AliPay);
				}
				//付款完成后，支付宝系统发送该交易状态通知
				response.getWriter().println("success");	//请不要修改或删除
			}else{//验证失败
				response.getWriter().println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 支付完成之后跳转页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/successReturnPage")
    @ResponseBody
	public ModelAndView successReturnPage(HttpServletRequest request, 
    		HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mobile/success_return_page");
		return mv;
	}
	
}
