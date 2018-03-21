package com.ycb.dao;

import com.ycb.entity.ReflectInfo;

public interface ReflectInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReflectInfo record);

    int insertSelective(ReflectInfo record);

    ReflectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReflectInfo record);

    int updateByPrimaryKey(ReflectInfo record);
}