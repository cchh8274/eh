package com.ycb.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.bean.PayConfig;
import com.ycb.util.Constants;
import com.ycb.util.HttpUtils;

public class PayTest {
	public static void main(String[] args) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("appid", "wx88cb890e1e079473");
		data.put("mch_id", "1496252192");
		data.put("nonce_str", WXPayUtil.generateNonceStr());
		data.put("out_trade_no", "00000000000000881264918313376957");
		String responseXML ="";
		try {
			responseXML = HttpUtils.submitPost("https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery",
					WXPayUtil.generateSignedXml(data, "dff3e9abba5296d1ca1e552e3475bb20"));
			System.out.println("Result =>>>"+responseXML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
