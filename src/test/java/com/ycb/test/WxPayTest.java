package com.ycb.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.util.Constants;
import com.ycb.util.HttpUtils;
import com.ycb.util.KeyUtils;
import com.ycb.util.Sign;

public class WxPayTest {
	public static void main(String[] args) {
		Map<String, String> data = new HashMap<String, String>();
		String nonce = KeyUtils.getNonce(); // 随机字符
		data.put("appid", "wx88cb890e1e079473");
		data.put("mch_id", "1496252192");
		data.put("nonce_str", nonce);
		data.put("sign_type", "MD5");
		data.put("body", Constants.body); // 商品描述
		data.put("detail", Constants.detil); // 商品详情
		data.put("out_trade_no", "4151215061515"); // 商户订单号
		data.put("fee_type", Constants.fee_type);// 标价币种
		data.put("total_fee", "10");// 标价金额
		data.put("spbill_create_ip", "117.73.154.210");// 终端IP
		data.put("time_start", "20180128210100");// 交易起始时间
		data.put("time_expire","20180128210700");// 交易结束时间
		// data.put("goods_tag", Contant.goods_tag);// 订单优惠标记
		data.put("notify_url", Constants.notify_url);// 通知地址
		data.put("trade_type", Constants.trade_type);// 交易类型
		// data.put("limit_pay", Contant.limit_pay);// 指定支付方式
		data.put("openid", "oYYPb0kX_sUAABZZF879tq9vYS44");// 用户标识
		String sign = KeyUtils.signMD5(data);
		data.put("sign", sign);
		String ss;
		try {
			ss = HttpUtils.submitPost(
					"https://api.mch.weixin.qq.com/pay/unifiedorder",
					WXPayUtil.mapToXml(data));
			System.out.println(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void Sss() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appId", "wx88cb890e1e079473");
		map.put("timeStamp", Sign.create_timestamp());
		map.put("nonceStr", "1W184wb9zy8NTBr4");
		map.put("package", "prepay_id=wx201801240025509dbea551380408080284");
		map.put("signType", "MD5");
		map.put("paySign", "F1C370446EC1D81B55FFDC4CFA33BC36");
		map.put("paySign", "6535C61DC4DEF1A6AD9895B205C8851A");
		
		String key = KeyUtils.signMap(map);
		System.out.println(key);
	}
}
