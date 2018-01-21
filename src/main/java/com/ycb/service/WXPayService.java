package com.ycb.service;

import java.util.Map;

import com.ycb.model.RequestOrder;
import com.ycb.model.WXParamerVO;

public interface WXPayService {

	Map<String, String> requsetWXpay(String order);

	String insert(RequestOrder vo);

}
