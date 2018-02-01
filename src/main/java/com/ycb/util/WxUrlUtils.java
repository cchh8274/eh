package com.ycb.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ycb.exception.WxException;

/**
 * 微信url工具类
 * 
 * @author chenghui
 *
 */
public class WxUrlUtils {
	
	private static final Logger logger = Logger.getLogger(WxUrlUtils.class);

	private static final String appid = "wx88cb890e1e079473";
	private static final String appSecret = "5982d81fbb3a64d413e9a4f1eabe0898";
	
	private static HashMap<String,String> params=new HashMap<String,String>();
	
	/**
	 * 获取openid
	 * 
	 * @param code
	 * @return
	 */
	public static String getOpenid(String code) {
		final String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		StringBuffer url = new StringBuffer();
		url.append(openid_url).append("?appid=").append(appid)
				.append("&secret=").append(appSecret).append("&code=")
				.append(code).append("&grant_type=authorization_code");
		return url.toString();
	}

	/**
	 * 
	 * @return
	 * @throws WxException 
	 */
	public static String getAccessToken() throws WxException {
		String url=Constants.ACCESS_TOKEN_URL;
		params.clear();
		params.put("grant_type", "client_credential");
		urlDefaul(url, params,true);
		return  url;
	}
	
	
	private static final void urlDefaul (String url, Map<String, String> params,boolean app) throws WxException{
		if(app){
			params.put("appid",appid);
			params.put("secret",appSecret);
		}
		urlFacatory(url, params);
	}
	
	/**
	 * 只有Get请求才能进行组装
	 * 
	 * @param map
	 * @return
	 * @throws WxException
	 */
	private static final void urlFacatory(String url, Map<String, String> params) throws WxException {
		if (StringUtils.isBlank(url)) {
			throw Exception("The URL can't be empty");
		}
		logger.info("this  url =>"+url);
		if (params == null) {
			return;
		}
		StringBuffer url1 = new StringBuffer(url);
		url1.append("?");
		for (String key : params.keySet()) {
			url1.append(key).append("=").append(params.get(key)).append("&");
		}
		url = url1.toString().trim().substring(0, url1.toString().length() - 1);
		logger.info("this append url =>"+url);
	}
	/**
	 * 扔出的异常
	 * @param message
	 * @return
	 */
	public static WxException Exception(String message) {
		WxException wxExcrption = WxException.getWxException();
		wxExcrption.setMessage(message);
		return wxExcrption;
	}
}
