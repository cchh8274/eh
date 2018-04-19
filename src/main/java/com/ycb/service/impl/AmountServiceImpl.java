package com.ycb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.service.AmountService;

@Service
public class AmountServiceImpl implements AmountService {

	@Autowired
	private TotalAmountMapper totalAmountMapper;
	/**
	 * 查询账户余额
	 */
	@Override
	public String queryamount(String openid) {
		return null;
	}


}
