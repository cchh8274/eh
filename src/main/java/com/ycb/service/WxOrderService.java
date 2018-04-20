package com.ycb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface WxOrderService {

	String queryCountManchine(String openID);

	List<Map<String, Object>> queryMachinebyOpenid(String openID) throws Exception;

	void queryWxOrderAll(Map<String, Object> map);

	List<String> queryUsergroupby();

	List<HashMap> queryDealList(String yesterday);

	void queryBatisinfo(HashMap hashMap);

	void insertBatch(List<HashMap> list) throws Exception;

}
