package com.ycb.dao;

import java.util.List;

import com.ycb.entity.TblSysMenu;

public interface TblSysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblSysMenu record);

    int insertSelective(TblSysMenu record);

    TblSysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblSysMenu record);

    int updateByPrimaryKey(TblSysMenu record);

	List<TblSysMenu> selectMenuAll();
}