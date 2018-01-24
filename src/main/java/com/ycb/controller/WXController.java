package com.ycb.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.model.RequestOrder;
import com.ycb.service.WXPayService;
import com.ycb.util.DateUtils;
import com.ycb.util.HttpUtils;
import com.ycb.util.IPAdressUtils;
import com.ycb.util.WxUrlUtils;

/**
 * 微信支付调起
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/ycb/wxpay")
public class WXController {

	private static final Logger logger = Logger.getLogger(WXController.class);

	@Autowired
	private WXPayService wxPayService;

	private static final String old = "yyyy年MM月dd日hh时mm分ss秒";
	private static final String format = "yyyyMMddHHmmss";

	/**
	 * 金额 /数据 机器 1.自己订单生成订单 2，微信请求下单
	 * 
	 * @return
	 */
	@RequestMapping(value = "requestOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> requestWXPay(RequestOrder vo,HttpServletRequest request) {
		logger.info("微信下单开始");
		String startTime=DateUtils.getbjTime();
		logger.info("订单开始时间为=>"+startTime);
		vo.setStartTime(startTime);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(DateUtils.paresDate(format, startTime));
		calendar.add(Calendar.MINUTE, 5);
		String endTime=DateUtils.formatDate(calendar.getTime(), format);
		logger.info("订单存活时间为=>"+endTime);
		vo.setEndTime(endTime);
		logger.info("微信发起的订单请求数据为=>" + vo.toString());
		String ipadree = IPAdressUtils.getIpAddr(request);
		logger.info("微信发起的订单请求IP地址为=>" + ipadree);
		vo.setIpadress(ipadree);
		Map<String,String> order = wxPayService.payOrder(vo);
		logger.info("微信发起的订单请求完成=>" + JSON.toJSONString(order));
		return order;
	}

	/**
	 * 支付接口响应
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/responseResult")
	@ResponseBody
	public String responseResult(String xml) throws Exception {
		logger.info("微信回调开始=>" +xml);
		// 转化xml 为map
		Map<String, String> resultMap = null;
		try {
			resultMap = WXPayUtil.xmlToMap(xml);
			logger.info("微信回调开始=>" +JSON.toJSONString(resultMap));
			wxPayService.callBackWXpay(resultMap);
			logger.info("微信回调完成=>" +JSON.toJSONString(resultMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return WXPayUtil.mapToXml(resultMap);
	}

	/**
	 * 获取用户的openID
	 * 
	 * @return
	 */
	@RequestMapping("/openid")
	@ResponseBody
	public Map<String, String> requestOpenid(String code) {
		logger.info("当前的请求成功 ,code为=》" + code);
		String url = WxUrlUtils.getOpenid(code);
		logger.info("授权转义的URL ,URL为=》" + url);
		String result = HttpUtils.submitGet(url);
		logger.info("请求微信=》返回的结果为=》" + result);
		JSONObject info = JSON.parseObject(result);
		Map<String, String> map = new HashMap<String, String>();
		logger.info("请求微信 JSON=》返回的结果为=》" + info);
		map.put("openid", (String) info.get("openid"));
		return map;
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public  Map<String, String>  config(String url){
		logger.info("获取配置信息");
		return wxPayService.config(url);
	}
}
