package com.ycb.service;

import java.util.List;

import com.ycb.entity.BankAmount;

public interface BankService {

	void addBankAmount(BankAmount bankAmount);

	List<BankAmount> searchBankAmount(String openID);

}
