package com.ycb.service.impl;

import com.ycb.dao.MachineMapper;
import com.ycb.entity.Machine;
import com.ycb.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhm on 2018/1/3.
 */
@Service
public class MachineServiceImp implements MachineService{


    @Autowired
    private MachineMapper machineMapper;

    @Override
    public void addMachine(Machine machine) {

        if(machine == null){

        }
        int i =  machineMapper.insert(machine);
        if(i < 1){

        }

    }

    @Override
    public Machine findMachine(int id) {
        if("".equals(id)){

        }
        Machine machine = machineMapper.selectByPrimaryKey(id);

        return machine;
    }

    @Override
    public void deleteMachine(int id) {

        int i = machineMapper.deleteByPrimaryKey(id);

        if(i < 1){

        }
    }

    @Override
    public void updateMachine(Machine machine) {

        int i = machineMapper.updateByPrimaryKey(machine);

        if(i < 1){

        }
    }
}
