package com.ycb.service;

import java.util.Map;

import com.ycb.model.WXParamerVO;

public interface WXPayService {

	Map<String, String> requsetWXpay(WXParamerVO vo);

}
