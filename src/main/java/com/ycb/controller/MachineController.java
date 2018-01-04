package com.ycb.controller;

import com.ycb.entity.Machine;
import com.ycb.service.MachineService;
import com.ycb.util.DataGridJson;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhm on 2018/1/3.
 */
@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "addMachine", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson addMachine(Machine machine){
        return machineService.addMachine(machine);
    }

    @RequestMapping(value = "findMachine", method = RequestMethod.POST)
    @ResponseBody
    public Machine queryMachine(int id){
        return machineService.findMachine(id);
    }

    @RequestMapping(value = "deleteMachine", method = RequestMethod.POST)
    public ReturnJson deleteMachine(int id){
        return machineService.deleteMachine(id);
    }

    @RequestMapping(value = "updateMachine", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson updateMachine(Machine machine){
        return machineService.updateMachine(machine);
    }

    @RequestMapping(value = "findMachineList", method = RequestMethod.POST)
    @ResponseBody
    public DataGridJson queryMachineList(PageUtil<Machine> pageUtil, Integer rows, Integer page){
        pageUtil.setCpage(page);
        pageUtil.setPageSize(rows);
        pageUtil = machineService.findMachineList(pageUtil);
        DataGridJson dj = new DataGridJson();
        dj.setTotal(pageUtil.getTotalCount());
        dj.setRows(pageUtil.getList());
        return dj;
    }

}
