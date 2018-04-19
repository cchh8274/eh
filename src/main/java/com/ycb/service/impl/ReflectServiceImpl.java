package com.ycb.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ycb.service.ReflectService;
@Service
public class ReflectServiceImpl implements ReflectService {

	/**
	 * 查询银行卡是否存在
	 */
	@Override
	public String queryBankAmount(String bank) {
		
		return null;
	}
	/**
	 * 体现
	 */
	@Override
	public void reflectMoney(Map<String, String> map) {
		//1.查询账户
		//2.判断余额
		//3.扣减金额
		//4.入体现明细
	}
}
