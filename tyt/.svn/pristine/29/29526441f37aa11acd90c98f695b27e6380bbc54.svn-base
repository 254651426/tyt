package com.bquan.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import com.bquan.bean.WeixinpayConfig;

import net.sf.json.JSONObject;



public class WeixinSignUtil {
	
	/**
	 * 生成微信支付时统一下单的参数
	 * @param weixinOrder
	 * @return
	 */
	public static String getAppTyxdXml(WeixinpayConfig order){
		String xml = 
				"<?xml version='1.0' encoding='UTF-8'?>"
				+"<xml>"
				   +"<appid>"+order.getAppid()+"</appid>"
				   +"<attach>"+order.getAttach()+"</attach>"
				   +"<body>"+order.getBody()+"</body>"
				   +"<mch_id>"+order.getMch_id()+"</mch_id>"
				   +"<nonce_str>"+order.getNonce_str()+"</nonce_str>"
				   +"<notify_url>"+order.getNotify_url()+"</notify_url>"
				   +"<out_trade_no>"+order.getOut_trade_no()+"</out_trade_no>"
				   +"<spbill_create_ip>"+order.getSpbill_create_ip()+"</spbill_create_ip>"
				   +"<total_fee>"+order.getTotal_fee()+"</total_fee>"
				   +"<trade_type>"+order.getTrade_type()+"</trade_type>"
				   +"<sign>"+order.getSign()+"</sign>"
				+"</xml>";
		return xml;
	}
	
	/**
	 * 查询订单时生成的xml
	 * @param weixinOrder
	 * @return
	 */
	public static String getAppCheckOrderXml(WeixinpayConfig weixinOrder){
		String xml = 
				"<?xml version='1.0' encoding='UTF-8'?>"
				+"<xml>"
				+"<appid>"+weixinOrder.getAppid()+"</appid>"
				+"<mch_id>"+weixinOrder.getMch_id()+"</mch_id>"
				+"<nonce_str>"+weixinOrder.getNonce_str()+"</nonce_str>"
				+"<transaction_id>"+weixinOrder.getTransaction_id()+"</transaction_id>"
				+"<sign>"+weixinOrder.getSign()+"</sign>"
				+"</xml>";
		return xml;
	}

	/**
	 * app微信支付统一下单接口时生成的签名字符串,然后将生成的签名字符串存到WeixinOrder对象中的sign属性中
	 * @param weixinOrder
	 * @return
	 */
	public static WeixinpayConfig getAppTyxdSign(WeixinpayConfig weixinOrder){
		String url = 
		   "appid="+weixinOrder.getAppid()
		   +"&attach="+weixinOrder.getAttach()
		   +"&body="+weixinOrder.getBody()
		   +"&mch_id="+weixinOrder.getMch_id()
		   +"&nonce_str="+weixinOrder.getNonce_str()
		   +"&notify_url="+weixinOrder.getNotify_url()
		   +"&out_trade_no="+weixinOrder.getOut_trade_no()
		   +"&spbill_create_ip="+weixinOrder.getSpbill_create_ip()
		   +"&total_fee="+weixinOrder.getTotal_fee()
		   +"&trade_type="+weixinOrder.getTrade_type()
		   +"&key="+weixinOrder.getKey();
		System.out.println(url);
		weixinOrder.setSign(MD5Util.MD5Encode(url,"UTF-8").toUpperCase());
		System.out.println(weixinOrder.getSign());
		return weixinOrder;   
	}
	
	/**
	 * html5生成支付时的sign
	 * @param weixinOrder
	 * @return
	 */
	public static WeixinpayConfig getAppPaySign(WeixinpayConfig weixinOrder){
//		"appId" ： "wx2421b1c4370ec43b",     //公众号名称，由商户传入     
//		"nonceStr" ： "e61463f8efa94090b1f366cccfbbb444", //随机串     
//        "package" ： "prepay_id=u802345jgfjsdfgsdg888",    
//        "timeStamp"：" 1395712654",         //时间戳，自1970年以来的秒数     
		//"signType" ： "MD5",
		System.out.println("------------------------");
         System.out.println(weixinOrder.getPrepayId());
         System.out.println("------------------------");
		
		String url = 
				"appId="+weixinOrder.getAppid()
				+"&nonceStr="+weixinOrder.getNonce_str()
				+"&package=prepay_id="+weixinOrder.getPrepayId()
				+"&signType=MD5"
				+"&timeStamp="+weixinOrder.getTimeNumber()
				+"&key="+weixinOrder.getKey();
		weixinOrder.setSign(MD5Util.MD5Encode(url,"UTF-8").toUpperCase());
		return weixinOrder;
	}
	
	/**
	 * 生成app客户端支付时所需要的参数
	 * @param weixinOrder
	 * @return
	 */
	public static Map<String,String> getClientPaySign(WeixinpayConfig weixinOrder){
		String timeNum = String.valueOf(new Date().getTime()/1000);
		String noncestr = RandomCodeUtil.getNumberRandomCode(6);
		String url = 
				"appid="+weixinOrder.getAppid()
				+"&noncestr="+noncestr
				+"&package=Sign=WXPay"
				+"&partnerid="+weixinOrder.getMch_id()
				+"&prepayid="+weixinOrder.getPrepayId()
				+"&timestamp="+timeNum
				+"&key="+weixinOrder.getKey();
		Map<String,String> response = new HashMap<String,String>();		
		response.put("app_id", weixinOrder.getAppid());
		response.put("mchid", weixinOrder.getMch_id());
		response.put("randomstring", noncestr);
		response.put("timenumber", timeNum);
		response.put("prepayid", weixinOrder.getPrepayId());
		response.put("sign", MD5Util.MD5Encode(url, "utf-8"));
		return response;
	}
	
	/**
	 * 生成查询订单时的sign
	 * @param weixinOrder
	 * @return
	 */
	public static WeixinpayConfig getCheckOrderSign(WeixinpayConfig weixinOrder){
		String url = 
				"appid="+weixinOrder.getAppid()
				+"&mch_id="+weixinOrder.getMch_id()
				+"&nonce_str="+weixinOrder.getNonce_str()
				+"&transaction_id="+weixinOrder.getTransaction_id()
				+"&key="+weixinOrder.getKey();
		weixinOrder.setSign(MD5Util.MD5Encode(url,"UTF-8").toUpperCase());		 
		return weixinOrder;
	}
	
	public static WeixinpayConfig getJsApiSign(WeixinpayConfig weixinOrder){
		String url = 
				"appid="+weixinOrder.getAppid()
				+"&mch_id="+weixinOrder.getMch_id()
				+"&nonce_str="+weixinOrder.getNonce_str()
				+"&transaction_id="+weixinOrder.getTransaction_id()
				+"&key="+weixinOrder.getKey();
		weixinOrder.setSign(MD5Util.MD5Encode(url,"UTF-8").toUpperCase());		 
		return weixinOrder;
	}
	/*
	 * 回调效验sign
	 * */
	public static WeixinpayConfig checkSign(WeixinpayConfig weixinOrder){
		String url = 
				"appid="+weixinOrder.getAppid()
				+"&attach="+weixinOrder.getAttach()
				+"&bank_type="+weixinOrder.getBank_type()
				+"&cash_fee="+weixinOrder.getCash_fee()
				+"&fee_type="+weixinOrder.getFee_type()
				+"&is_subscribe="+weixinOrder.getIs_subscribe()
				+"&mch_id="+weixinOrder.getMch_id()
				+"&nonce_str="+weixinOrder.getNonce_str()
				+"&out_trade_no="+weixinOrder.getOut_trade_no()
				+"&result_code="+weixinOrder.getResult_code()
				+"&return_code="+weixinOrder.getReturn_code()
				+"&time_end="+weixinOrder.getTime_end()
				+"&total_fee="+weixinOrder.getTotal_fee()
				+"&trade_type="+weixinOrder.getTrade_type()
				+"&transaction_id="+weixinOrder.getTransaction_id()
				+"&key="+weixinOrder.getKey();
		System.out.println("生成参数前："+url);
		weixinOrder.setSign(MD5Util.MD5Encode(url,"UTF-8").toUpperCase());		 
		return weixinOrder;
	}
	public static boolean checkIsSignValidFromResponseString(JSONObject resultJson,WeixinpayConfig wc)  {

        String signFromAPIResponse = resultJson.getJSONObject("xml").getJSONArray("sign").getString(0);
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
        	System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        wc.setAttach(resultJson.getJSONObject("xml").getJSONArray("attach").getString(0));
        wc.setBank_type(resultJson.getJSONObject("xml").getJSONArray("bank_type").getString(0));
        wc.setCash_fee(resultJson.getJSONObject("xml").getJSONArray("cash_fee").getString(0));
        wc.setFee_type(resultJson.getJSONObject("xml").getJSONArray("fee_type").getString(0));
        wc.setIs_subscribe(resultJson.getJSONObject("xml").getJSONArray("is_subscribe").getString(0));
        wc.setMch_id(resultJson.getJSONObject("xml").getJSONArray("mch_id").getString(0));
        wc.setNonce_str(resultJson.getJSONObject("xml").getJSONArray("nonce_str").getString(0));
        wc.setOut_trade_no(resultJson.getJSONObject("xml").getJSONArray("out_trade_no").getString(0));
        wc.setResult_code(resultJson.getJSONObject("xml").getJSONArray("result_code").getString(0));
        wc.setReturn_code(resultJson.getJSONObject("xml").getJSONArray("return_code").getString(0));
        wc.setTime_end(resultJson.getJSONObject("xml").getJSONArray("time_end").getString(0));
        wc.setTotal_fee(resultJson.getJSONObject("xml").getJSONArray("total_fee").getString(0));
        wc.setTrade_type(resultJson.getJSONObject("xml").getJSONArray("trade_type").getString(0));
        wc.setTransaction_id(resultJson.getJSONObject("xml").getJSONArray("transaction_id").getString(0));
        wc = WeixinSignUtil.checkSign(wc);
        String sign = wc.getSign();
        System.out.println("当前的回包里面的签名是:" + sign);
        if(!signFromAPIResponse.equals(sign)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
        	System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        System.out.println("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime()/1000);
	}
}
