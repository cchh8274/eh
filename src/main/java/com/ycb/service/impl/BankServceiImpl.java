package com.ycb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.BankAmountMapper;
import com.ycb.entity.BankAmount;
import com.ycb.service.BankService;
import com.ycb.service.TblBankamountInfo;
@Service
public class BankServceiImpl implements BankService {
	
	@Autowired
	private  BankAmountMapper bankAmountMapper;
	
	
	@Override
	public void addBankAmount(BankAmount bankAmount) {
		bankAmount.setCreatetime(new Date());
		bankAmountMapper.insert(bankAmount);	
	}


	/**
	 * 保存银行卡信息
	 */
	@Override
	public int addBankAmount(TblBankamountInfo bankAmount) {
		this.se
		return 0;
	}

	/**
	 * 查询银行卡信息列表
	 */
	@Override
	public List<TblBankamountInfo> queryBankAmount(String openid) {
		return null;
	}
	
}
