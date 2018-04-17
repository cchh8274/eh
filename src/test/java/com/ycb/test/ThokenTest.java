package com.ycb.test;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ycb.util.HttpUtils;
import com.ycb.util.IDGeneratorTools;

public class ThokenTest {

	private static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static String appid = "wx88cb890e1e079473";
	private static String secret = "5982d81fbb3a64d413e9a4f1eabe0898";

	public static void main(String[] args) {
//		try {
//			String url = URL + "&appid=" + appid + "&secret=" + secret;
//			String response = HttpUtils.submitGet(url);
//			System.out.println(response);
//			Map<String, Object> map = JSONObject.parseObject(response);
//			for (String key : map.keySet()) {
//				System.out.println("key={"+key+"},value={"+map.get(key)+"}");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("20180327104840".length());
	}

}
