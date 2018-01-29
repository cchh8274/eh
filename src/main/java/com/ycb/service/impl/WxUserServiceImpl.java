package com.ycb.service.impl;

import org.springframework.stereotype.Service;

import com.ycb.service.WxUserService;
import com.ycb.util.HttpUtils;
@Service
public class WxUserServiceImpl implements WxUserService {
	
	public static final String session="6_L8lHye85Yq_P2zOONX2EsZesdaPvj7_gHQ2fXXqVvX6hbtAYSf2-NnYLlRf0R2Qp8PlhQ01fHOOxQpfDn7VaHdj-D2GD3lNx8A3ObfCG-uLay0EUxBR-E1E5S8wLlKkrChqXifX_wltsenjiDLBeAJAVDT";
	
	@Override
	public String getUserInfo(String openID) throws Exception {
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ session
				+ "&openid="+openID+"&lang=zh_CN";
		String response=HttpUtils.submitGet(url);
		return response;
	}

}
