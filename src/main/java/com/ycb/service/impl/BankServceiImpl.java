package com.ycb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.BankAmountMapper;
import com.ycb.entity.BankAmount;
import com.ycb.service.BankService;
@Service
public class BankServceiImpl implements BankService {
	
	@Autowired
	private  BankAmountMapper bankAmountMapper;
	
	
	@Override
	public void addBankAmount(BankAmount bankAmount) {
		bankAmount.setCreatetime(new Date());
		bankAmountMapper.insert(bankAmount);	
	}


	@Override
	public List<BankAmount> searchBankAmount(String openID) {
		return bankAmountMapper.selectBankAmountByOpenID(openID);
	}

}
