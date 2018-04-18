package com.ycb.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.ycb.bean.PayConfig;

public class PatTest {
	public static void main(String[] args) {
		//00000000000000881199230433138420
		PayConfig  con=new PayConfig();
		WXPay wxpay = new WXPay(con,WXPayConstants.SignType.MD5,true);
		Map<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", "00000000000000881199230433138420");
		try {
			Map<String, String> resp =wxpay.orderQuery(data);
			System.out.println(JSON.toJSON(resp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
