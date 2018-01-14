package com.ycb.dao;


import com.ycb.entity.Machine;
import com.ycb.entity.Unit;
import com.ycb.util.PageUtil;

import java.util.List;

public interface MachineMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

    List<Machine> selectMachineList(PageUtil<Machine> pageUtil);

    Integer selectCount(PageUtil<Machine> pageUtil);

    int deleteMachineArr(List<Integer> list);

	Integer queryMachineCount(Integer i);
	Integer queryMachineMonthCount(Integer i);

	List<Unit> selectUnit();

	List<Machine> queryUnitMat(String name);

	
}