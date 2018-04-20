package com.ycb.service;

public interface EarningService {

	String queryEarningMoney(String openID);

	void queryNoSyncData() throws Exception;

}
