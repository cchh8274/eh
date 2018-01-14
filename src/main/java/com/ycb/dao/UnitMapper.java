package com.ycb.dao;

import java.util.List;

import com.ycb.entity.Unit;

public interface UnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);

	List<Unit> selectByAreaName(String name);
}