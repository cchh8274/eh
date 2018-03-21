package com.ycb.dao;

import com.ycb.entity.Reflect;

public interface ReflectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reflect record);

    int insertSelective(Reflect record);

    Reflect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reflect record);

    int updateByPrimaryKey(Reflect record);
}