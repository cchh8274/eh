package com.ycb.controller;

import com.ycb.entity.Machine;
import com.ycb.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhm on 2018/1/3.
 */
@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "addMachine", method = RequestMethod.POST)
    public void addMachine(Machine machine){

        machineService.addMachine(machine);
    }

    @RequestMapping(value = "findMachine", method = RequestMethod.POST)
    public void queryMachine(int id){
        Machine machine = machineService.findMachine(id);
    }

    @RequestMapping(value = "deleteMachine", method = RequestMethod.POST)
    public void deleteMachine(int id){
        machineService.deleteMachine(id);
    }

    @RequestMapping(value = "updateMachine", method = RequestMethod.POST)
    public void updateMachine(Machine machine){
        machineService.updateMachine(machine);
    }


}
