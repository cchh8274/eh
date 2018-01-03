package com.ycb.dao;

import com.ycb.entity.Amount;

public interface AmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Amount record);

    int insertSelective(Amount record);

    Amount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Amount record);

    int updateByPrimaryKey(Amount record);
}