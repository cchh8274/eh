package com.ycb.bean;
/**
 * 请求微信下单的bean
 * @author chenghui
 *
 */
public class RequestOrder {
	private String  openid;  //openid
	private String  areacode; //区域编码
	private String  unitcode;// 大学编码
	private String  machine;//机器编码
	private String  num; //单位 份数
	private String  totalfee; //金额
	private String  startTime; //开始时间
	private String  endTime; //结束时间
	private String  orderno; //订单号
	private String  ipadress; //ipadress下单的ip地址
	private String  notifyurl; //回调地址
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getUnitcode() {
		return unitcode;
	}
	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
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
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getIpadress() {
		return ipadress;
	}
	public void setIpadress(String ipadress) {
		this.ipadress = ipadress;
	}
	@Override
	public String toString() {
		return "RequestOrder [openid=" + openid + ", areacode=" + areacode
				+ ", unitcode=" + unitcode + ", machine=" + machine + ", num="
				+ num + ", totalfee=" + totalfee + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", orderno=" + orderno
				+ ", ipadress=" + ipadress + ", notifyurl=" + notifyurl + "]";
	}
	
		
}
