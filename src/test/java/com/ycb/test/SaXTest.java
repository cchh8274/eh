package com.ycb.test;

import java.util.HashMap;
import java.util.Map;

import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.util.HttpUtils;

public class SaXTest {
	public static void main(String[] args) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mch_id", "1496252192");
			map.put("nonce_str", WXPayUtil.generateNonceStr());
			map.put("sign", WXPayUtil.generateSignature(map, "ycbqc18103517010zly17611540713ch"));
			String xml = WXPayUtil.mapToXml(map);
			System.out.println(xml);
			String ss = HttpUtils.submitPost(
					"https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey",
					xml);
			System.out.println(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
