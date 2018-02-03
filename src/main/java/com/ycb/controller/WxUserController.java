package com.ycb.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.service.WxOrderService;
import com.ycb.service.WxUserService;
import com.ycb.util.HttpUtils;
import com.ycb.util.WxUrlUtils;

/**
 * 微信用户管理
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/wx")
public class WxUserController {

	private static final Logger logger = Logger
			.getLogger(WxUserController.class);

	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxOrderService wxOrderService;
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
