package com.ycb.service;

import java.util.Map;

import com.ycb.model.RequestOrder;
import com.ycb.model.WXParamerVO;

public interface WXPayService {



	void requsetWXpay(Map<String, String> resultMap);

	void callBackWXpay(Map<String, String> resultMap)throws Exception;

	Map<String, String> requsetWXpay(WXParamerVO vo);

	Map<String,String> payOrder(RequestOrder vo);

	Map<String, String> config(String url);


}
