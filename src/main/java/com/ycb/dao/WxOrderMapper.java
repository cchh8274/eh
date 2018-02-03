package com.ycb.dao;

import java.util.List;

import com.ycb.entity.WxOrder;

public interface WxOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxOrder record);

    int insertSelective(WxOrder record);

    WxOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxOrder record);

    int updateByPrimaryKey(WxOrder record);
    
    WxOrder queryOrder(String orderno);

  	String queryCountManchine(String openID);

  	List<WxOrder> queryMachinebyOpenid(String openID);
}