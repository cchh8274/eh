package com.ycb.service;

import com.ycb.entity.Machine;
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
}
