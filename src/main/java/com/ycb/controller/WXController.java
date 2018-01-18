package com.ycb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.service.WXPayService;

/**
 * 微信支付调起
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/ycb/wxpay")
public class WXController {
	@Autowired
	private WXPayService wxPayService;
	
	
	/**
	 * 金额 /数据 机器 
	 * 1.自己订单生成订单
	 * 2，微信请求下单
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public Map<String, String> requestWXPay() {
		wxPayService.requsetWXpay();
		return null;
	}
	/**
	 * 支付接口响应
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public Map<String, String> responseResult() {
		wxPayService.requsetWXpay();
		return null;
	} 
	 
}
