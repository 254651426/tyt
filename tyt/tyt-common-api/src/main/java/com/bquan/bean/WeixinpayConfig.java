package com.bquan.bean;

import java.io.Serializable;

public class WeixinpayConfig implements Serializable{
	/* 公众账号ID */
	private String appid ;
	/* 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 */
	private String attach;
	/* 商品描述 */
	private String body;
	/* 商户号 */
	private String mch_id ;
	/* 随机字符串，不长于32位 */
	private String nonce_str;
	/* 回调地址 */
	private String notify_url;
	/* 商户订单号 */
	private String out_trade_no;
	/* 终端IP */
	private String spbill_create_ip;
	/* 总金额 */
	private String total_fee;
	/* 交易类型
		JSAPI--公众号支付、
		NATIVE--原生扫码支付、
		APP--app支付，统一下单接口trade_type的传参可参考这里
		MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	 */
	private String trade_type;
	/* 签名时用到的key */
	private String key ;
	/* 签名后生成的32为字符串 */
	private String sign;
	/* 调用预支付订购接口后返回的预支付id */
	private String prepayId;
	/* 时间戳 */
	private long timeNumber;
	/* 微信订单号 */
	private String transaction_id;
	/* 用户的openid */
	private String openid;
	private String bank_type;
	private String cash_fee;
	private String fee_type;
	private String result_code;
	private String return_code;
	private String time_end;
	private String is_subscribe;
	public WeixinpayConfig(String appid,String mch_id,String key){
		/* 初始化支付配置 */
//		this.appid = WechatConstant._cr.get("appId");
//		this.mch_id= WechatConstant._cr.get("mpId");
//		this.key = WechatConstant._cr.get("aesKey");
//		this.appid = "wx24e6597910a4b365";
//		this.mch_id= "1312912101";
//		this.key = "weixinpaykeyys2016weixinpaykeyys";
//		this.appid = "wx2eea98b5e0276a5d";
//		this.mch_id= "1226672102";
//		this.key = "wxpaykeyguoxin2015wxpaykeyguoxin";
		
		this.appid = appid;//"wxe3dcfec2b164eada";
		this.mch_id= mch_id;//"1442577302";
		this.key = key;//"zxsljxxjsyxgslxkzxsljxxjsyxgslxk";
		
//		this.appid = "wx40a7c4c9bd191506";
//		this.mch_id= "1288566701";
//		this.key = "1qaz2wsx3edc4rfv5tgb6yhn7ujm8ik9";
	}
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public long getTimeNumber() {
		return timeNumber;
	}

	public void setTimeNumber(long timeNumber) {
		this.timeNumber = timeNumber;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	
}
