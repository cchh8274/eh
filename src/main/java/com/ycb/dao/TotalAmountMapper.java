package com.ycb.dao;

import com.ycb.entity.TotalAmount;

public interface TotalAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TotalAmount record);

    int insertSelective(TotalAmount record);

    TotalAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TotalAmount record);

    int updateByPrimaryKey(TotalAmount record);

	TotalAmount selectAmonut(String openid);
}