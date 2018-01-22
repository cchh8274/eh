package com.ycb.util;
/**
 * 微信url工具类
 * @author chenghui
 *
 */
public class WxUrlUtils {
	private static final String appid="wx88cb890e1e079473";
	private static final String appSecret="5982d81fbb3a64d413e9a4f1eabe0898";
	
	
	/**
	 * 获取openid
	 * @param code
	 * @return
	 */
	public static String getOpenid(String code){
		final String openid_url="https://api.weixin.qq.com/sns/oauth2/access_token";
		StringBuffer  url=new StringBuffer();
		url.append(openid_url)
		.append("&appid=").append(appid)
		.append("&secret=").append(appSecret)
		.append("&code=").append(code)
		.append("&grant_type=authorization_code");
		return url.toString();
	}

}
