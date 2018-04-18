package com.ycb.bean;

public class RequestOrder {
	private String  openid;  //openid
	private String  area; //区域
	private String  unit;// 大学
	private String  mach;//机器
	private String  num; //单位 份数
	private String  totalfee; //金额
	private String  startTime; //开始时间
	private String  endTime; //结束时间
	private String  orderno; //订单号
	private String  ipadress; //ipadress
	private String  url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIpadress() {
		return ipadress;
	}
	public void setIpadress(String ipadress) {
		this.ipadress = ipadress;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMach() {
		return mach;
	}
	public void setMach(String mach) {
		this.mach = mach;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	@Override
	public String toString() {
		return "RequestOrder [area=" + area + ", unit=" + unit + ", mach="
				+ mach + ", num=" + num + ", totalfee=" + totalfee
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", openid=" + openid + ", orderno=" + orderno + ", ipadress="
				+ ipadress + "]";
	}
	
		
}
