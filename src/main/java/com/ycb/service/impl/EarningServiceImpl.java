package com.ycb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.WxEarningsMapper;
import com.ycb.service.EarningService;

@Service
public class EarningServiceImpl implements EarningService{
	@Autowired
	private WxEarningsMapper wxEarningsMapper;
	
	@Override
	public String queryEarningMoney(String openID) {
		
		
		return null;
	}

}
