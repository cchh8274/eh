package com.ycb.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.http.util.IPAddress;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.model.RequestOrder;
import com.ycb.model.WXParamerVO;
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
	@Autowired
	private WXPayService wxPayService;

	private static final String old = "yyyy年MM月dd日";
	private static final String format = "yyyyMMdd";

	/**
	 * 金额 /数据 机器 1.自己订单生成订单 2，微信请求下单
	 * 
	 * @return
	 */
	@RequestMapping(value = "requestOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> requestWXPay(RequestOrder vo,
			HttpServletRequest request) {
		vo.setStartTime(DateUtils.format(old, format, vo.getStartTime()));
		vo.setEndTime(DateUtils.format(old, format, vo.getEndTime()));
		String ipadree=IPAdressUtils.getIpAddr(request);
		String order = wxPayService.insert(vo);

		vo.setOrderno(order);
		// e Map<String, String> map = wxPayService.requsetWXpay(order);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "success");
		return map;
	}

	/**
	 * 支付接口响应
	 * 
	 * @return
	 */
	@RequestMapping("/responseResult")
	@ResponseBody
	public String responseResult(String xml) {
		// 转化xml 为map
		Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
		wxPayService.callBackWXpay(resultMap);
		return WXPayUtil.mapToXml(resultMap);
	}

	/**
	 * 获取用户的openID
	 * 
	 * @return
	 */
	@RequestMapping("/openid")
	@ResponseBody
	public String requestOpenid(String code) {
		String url = WxUrlUtils.getOpenid(code);
		String result = HttpUtils.submitGet(url);
		JSONObject info = JSON.parseObject(result);
		return (String) info.get("openid");
	}
}
