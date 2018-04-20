package com.ycb.service;

import java.util.HashMap;
import java.util.List;
/**
 * 账户管理
 * @author chenghui
 *
 */
public interface AmountService {

	String queryamount(String openid);
	
	List<String> queryAccount(String yesterday);

	void insertAccount(List<HashMap> batchlist) throws Exception;
}
