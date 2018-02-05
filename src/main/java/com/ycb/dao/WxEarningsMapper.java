package com.ycb.dao;

import com.ycb.entity.WxEarnings;

public interface WxEarningsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxEarnings record);

    int insertSelective(WxEarnings record);

    WxEarnings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxEarnings record);

    int updateByPrimaryKey(WxEarnings record);
}