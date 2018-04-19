package com.ycb.util;
/**
 * 常量类
 * @author chenghui
 *
 */
public class Constants {
	//币种
	public static final String fee_type = "CNY";
	public static final String goods_tag = "";
	//回调的URL
	public static final String notify_url = "http://ch.jwangkun.com/eh/ycb/wxpay/responseResult.do";
	//调用方式
	public static final String TRADE_TYPE = "JSAPI";
	//是否指定支付方式
	public static final String limit_pay = "no_credit";
	//商品名称
	public static final String body = "24咖啡-机器";
	public static final String detil = "咖啡机名额购买";
	public static final String APPID = "wx88cb890e1e079473";
	public static final String SECRET = "5982d81fbb3a64d413e9a4f1eabe0898";

	public static final String MCH_ID = "1496252192";
	
	public  static final String USERINFO_URL="";
	
	public  static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token";
	public  static final String WX_USER_LIST="https://api.weixin.qq.com/cgi-bin/user/get";
	public  static final String WX_USER_INFO="https://api.weixin.qq.com/cgi-bin/user/info";
	
	public  static final String WX_PAY_ORDER="https://api.mch.weixin.qq.com/pay/unifiedorder";
		
}
