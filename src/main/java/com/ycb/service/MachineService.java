package com.ycb.service;

import com.ycb.entity.Machine;

/**
 * Created by zhm on 2018/1/3.
 */
public interface MachineService {
    
    void addMachine(Machine machine);

    Machine findMachine(int id);

    void deleteMachine(int id);

    void updateMachine(Machine machine);
}
