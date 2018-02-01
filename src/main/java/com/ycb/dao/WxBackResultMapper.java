package com.ycb.dao;

import com.ycb.entity.WxBackResult;

public interface WxBackResultMapper {
    int insert(WxBackResult record);

    int insertSelective(WxBackResult record);
}