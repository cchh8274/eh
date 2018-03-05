package com.ycb.service;

import java.util.List;
import java.util.Map;

import com.ycb.entity.WxUser;

public interface WxOrderService {

	String queryCountManchine(String openID);

	List<Map<String, Object>> queryMachinebyOpenid(String openID) throws Exception;

	void queryWxOrderAll(Map<String, Object> map);

	List<String> queryUsergroupby();

}
