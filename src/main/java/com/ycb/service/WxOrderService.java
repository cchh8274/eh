package com.ycb.service;

import java.util.List;
import java.util.Map;

public interface WxOrderService {

	String queryCountManchine(String openID);

	List<Map<String, Object>> queryMachinebyOpenid(String openID) throws Exception;

}
