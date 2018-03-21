package com.ycb.dao;

import java.util.List;

import com.ycb.entity.WxorderInfo;

public interface WxorderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxorderInfo record);

    int insertSelective(WxorderInfo record);

    WxorderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxorderInfo record);

    int updateByPrimaryKey(WxorderInfo record);

	List<WxorderInfo> select();
}