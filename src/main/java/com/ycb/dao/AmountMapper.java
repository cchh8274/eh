package com.ycb.dao;

import java.util.List;

import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.util.PageUtil;

public interface AmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Amount record);

    int insertSelective(Amount record);

    Amount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Amount record);

    int updateByPrimaryKey(Amount record);

	Amount queryMyAmount(String userID);

	List<Machine> queryAllAmount(PageUtil<Machine> pageUtil);

	Integer selectCount(PageUtil<Machine> pageUtil);
}