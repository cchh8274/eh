package com.ycb.bean;

/**
 * 
 * @author chenghui
 *
 *公众账号ID appid 
 *商户号 mchid 
 *设备号 deviceinfo 
 *随机字符串 noncestr 
 *签名 sign 是
 *签名类型signtype
 *商品描述 body 
 *商品详情 detail 
 *附加数据 attach 
 *商户订单号 outtradeno 
 *标价币种 feetype
 *标价金额 totalfee 
 *终端IP spbillcreateip 
 *交易起始时间 timestart 
 *交易结束时间 timeexpire
 *订单优惠标记 goodstag 
 *通知地址 notifyurl 
 *交易类型 tradetype 
 *商品ID productid 
 *指定支付方式 limitpay 
 *用户标识 openid
 */
public class WXParamerVO {
	 private String  appid; 
	 private String  mchid; 
	 private String  deviceinfo; 
	 private String  noncestr; 
	 private String  sign; 
	 private String signtype;
	 private String  body; 
	 private String detail; 
	 private String  attach; 
	 private String outtradeno; 
	 private String  feetype;
	 private String totalfee; 
	 private String  spbillcreateip; 
	 private String  timestart; 
	 private String  timeexpire;
	 private String goodstag; 
	 private String  notifyurl; 
	 private String  tradetype; 
	 private String  productid; 
	 private String limitpay; 
	 private String  openid;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getDeviceinfo() {
		return deviceinfo;
	}
	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSigntype() {
		return signtype;
	}
	public void setSigntype(String signtype) {
		this.signtype = signtype;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOuttradeno() {
		return outtradeno;
	}
	public void setOuttradeno(String outtradeno) {
		this.outtradeno = outtradeno;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	public String getSpbillcreateip() {
		return spbillcreateip;
	}
	public void setSpbillcreateip(String spbillcreateip) {
		this.spbillcreateip = spbillcreateip;
	}
	public String getTimestart() {
		return timestart;
	}
	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}
	public String getTimeexpire() {
		return timeexpire;
	}
	public void setTimeexpire(String timeexpire) {
		this.timeexpire = timeexpire;
	}
	public String getGoodstag() {
		return goodstag;
	}
	public void setGoodstag(String goodstag) {
		this.goodstag = goodstag;
	}
	public String getNotifyurl() {
		return notifyurl;
	}
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getLimitpay() {
		return limitpay;
	}
	public void setLimitpay(String limitpay) {
		this.limitpay = limitpay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	 
}


