package com.ycb.service;

import java.util.List;

import com.ycb.controller.BankAmount;

import cn.kanmars.ecm.entity.TblBankamountInfo;

public interface BankService {

	int addBankAmount(TblBankamountInfo bankAmount);

	List<TblBankamountInfo> queryBankAmount(String openid);

}
