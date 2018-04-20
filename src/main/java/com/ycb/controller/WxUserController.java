package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import cn.com.xbase.frame.util.HttpUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycb.service.WxOrderService;
import com.ycb.service.WxUserService;
import com.ycb.util.WxUrlUtils;

/**
 * 微信用户管理
 * 静默登录,用户无感知的情况下登录
 * 1.微信用户订单页面登录 
 * 2.我的页面登录
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/wx")
public class WxUserController {

	private static final Logger LOGGER = Logger.getLogger(WxUserController.class);

	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxOrderService wxOrderService;
	
	

	/**
	 * 获取用户的openID
	 * 
	 * @return
	 */
	@RequestMapping("/openid")
	@ResponseBody
	public Map<String, String> requestOpenid(String code) {
		LOGGER.info("当前的请求成功 ,code为=》" + code);
		String url = WxUrlUtils.getOpenid(code);
		LOGGER.info("授权转义的URL ,URL为=》" + url);
		String result = HttpUtils.submitGet(url);
		LOGGER.info("请求微信=》返回的结果为=》" + result);
		JSONObject info = JSON.parseObject(result);
		Map<String, String> map = new HashMap<String, String>();
		LOGGER.info("请求微信 JSON=》返回的结果为=》" + info);
		map.put("openid", (String) info.get("openid"));
		return map;
	}
	
	/**
	 * 微信用户登录
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserInfo")
	public String getUserInfo(String code) {
		logger.info("当前请求是我的页面微信登录..");
		try {
			logger.info("当前登录的微信用户的openID=>" + code);
			logger.info("当前的请求成功 ,code为=》" + code);
			String url = WxUrlUtils.getOpenid(code);
			logger.info("授权转义的URL ,URL为=》" + url);
			String result = HttpUtils.submitGet(url);
			logger.info("请求微信=》返回的结果为=》" + result);
			JSONObject info = JSON.parseObject(result);
			logger.info("请求微信 JSON=》返回的结果为=》" + info);
			String userurl="https://api.weixin.qq.com/sns/userinfo?"
					+ "access_token="+info.get("access_token")
					+ "&openid="+info.get("openid")
					+ "&lang=zh_CN";
			String response=HttpUtils.submitGet(userurl);
			Map<String, Object> map=JSON.parseObject(response);
			String num=wxOrderService.queryCountManchine((String) map.get("openid"));
			logger.info("获取微信用户信息 JSON=》返回的结果为=》" + response);
			logger.info("查询到的咖啡机数据量为=》" + num);
			map.put("cofNum", num);
			return  JSON.toJSONString(map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	
}
