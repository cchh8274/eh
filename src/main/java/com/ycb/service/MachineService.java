package com.ycb.service;

import java.util.List;

import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.entity.Unit;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

/**
 * Created by zhm on 2018/1/3.
 */
public interface MachineService {
    ReturnJson addMachine(Machine machine);

    Machine findMachine(int id);

    ReturnJson deleteMachine(int id);

    ReturnJson updateMachine(Machine machine);

    PageUtil<Machine> findMachineList(PageUtil<Machine> pageUtil);

    ReturnJson deleteMachineArr(String ids);

	Integer queryMachineCount(Integer i);

	Integer queryMachineCountMonth(Integer i);

	List<Unit> selectUnit();

	List<Machine> queryUnitMat(String name);
}
