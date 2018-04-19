package com.ycb.service;


import com.ycb.exception.BaseException;

import cn.kanmars.ecm.entity.TblBankamountInfo;

public interface BankService {

	int addBankAmount(TblBankamountInfo bankAmount) throws BaseException;

	String queryBankAmount(String openid);

}
