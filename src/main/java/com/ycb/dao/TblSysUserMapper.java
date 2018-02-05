package com.ycb.dao;

import java.util.Map;

import com.ycb.entity.TblSysUser;

public interface TblSysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblSysUser record);

    int insertSelective(TblSysUser record);

    TblSysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblSysUser record);

    int updateByPrimaryKey(TblSysUser record);

	TblSysUser login(String name, String password);

	TblSysUser login(Map<String, String> map);
}