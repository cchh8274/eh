package com.ycb.dao;

import java.util.List;

import com.ycb.entity.BankAmount;

public interface BankAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankAmount record);

    int insertSelective(BankAmount record);

    BankAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankAmount record);

    int updateByPrimaryKey(BankAmount record);

	List<BankAmount> selectBankAmountByOpenID(String openID);
}