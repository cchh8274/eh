package com.ycb.service;

import java.util.Map;

public interface WXPayService {
	void callBackWXpay(Map<String, String> resultMap) throws Exception;

	Map<String, String> config(String url);

}
