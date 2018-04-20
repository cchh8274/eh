package com.ycb.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.HttpUtils;
import cn.kanmars.ecm.dao.TblWxUserInfoMapper;

import com.alibaba.fastjson.JSONObject;
import com.ycb.service.WxUserService;
@Service
public class WxUserServiceImpl implements WxUserService {
	
	public static final String session="6_L8lHye85Yq_P2zOONX2EsZesdaPvj7_"
			+ "gHQ2fXXqVvX6hbtAYSf2-NnYLlRf0R2Qp8PlhQ01fHOOxQpfDn7V"
			+ "aHdj-D2GD3lNx8A3ObfCG-uLay0EUxBR-E1E5S8wLlKkrChqXifX_wlts"
			+ "enjiDLBeAJAVDT";
	@Autowired
	private TblWxUserInfoMapper tblWxUserInfoMapper;
	
	@Override
	public String getUserInfo(String openID) throws Exception {
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ getAccToken()
				+ "&openid="+openID+"&lang=zh_CN";
		String response=HttpUtils.submitGet(url);
		return response;
	}
	
	
	
	
	private  String  getAccToken(){
		  String url="https://api.weixin.qq.com/cgi-bin/token?"
		  		+ "grant_type=client_credential"
		  		+ "&appid=wx88cb890e1e079473"
		  		+ "&secret=5982d81fbb3a64d413e9a4f1eabe0898";
			String response=HttpUtils.submitGet(url);
		JSONObject  token=JSONObject.parseObject(response);
		return (String) token.get("access_token");
	}




	@Override
	public void insetListWxUser(List<HashMap> userInfos) throws Exception{
		tblWxUserInfoMapper.insertBatch(userInfos);
	}




	@Override
	public String queryNextOpenid() {
		return null;
	}
}
