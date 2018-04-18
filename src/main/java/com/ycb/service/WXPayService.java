package com.ycb.service;

import java.util.Map;

import com.ycb.bean.RequestOrder;

public interface WXPayService {
	void callBackWXpay(Map<String, String> resultMap) throws Exception;

	Map<String, String> payOrder(RequestOrder vo);

	Map<String, String> config(String url);

}
