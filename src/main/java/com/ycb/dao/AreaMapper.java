package com.ycb.dao;

import java.util.List;

import com.ycb.entity.Area;
import com.ycb.util.PageUtil;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

	List<Area> selectAreaList(PageUtil<Area> pageUtil);

	Integer selectCount(PageUtil<Area> pageUtil);

	int deleteAreaArr(List<Integer> list);

	List<Area> selectArea();
}