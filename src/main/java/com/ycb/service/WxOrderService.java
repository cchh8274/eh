package com.ycb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface WxOrderService {

	String queryCountManchine(String openID);

	List<Map<String, Object>> queryMachinebyOpenid(String openID) throws Exception;

	void queryWxOrderAll(Map<String, Object> map);

	List<String> queryUsergroupby();
	/**
	 * 查询支付成功的订单
	 * @param yesterday
	 * @return
	 */
	List<HashMap> queryDealList(String yesterday);
	/**
	 * 查询订单基础信息
	 * @param hashMap
	 */
	void queryBatisinfo(HashMap hashMap);
	/**
	 * 批量插入订单数据
	 * @param list
	 * @throws Exception
	 */
	void insertBatch(List<HashMap> list) throws Exception;

	List<HashMap> queryUserManinche();


}
