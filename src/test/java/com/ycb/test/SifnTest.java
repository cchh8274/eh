package com.ycb.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycb.util.HttpUtils;

public class SifnTest {
	public static void main(String[] args) {
		
		String token=HttpUtils.submitGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx88cb890e1e079473&secret=5982d81fbb3a64d413e9a4f1eabe0898");
		JSONObject oo=(JSONObject) JSON.parse(token);
		String ks="https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token="+oo.getString("access_token")+"&type=jsapi";
		String k=HttpUtils.submitGet(ks);
		String ss= ((JSONObject)JSON.parse(k)).getString("ticket");
		System.out.println(ss);
	}
}
