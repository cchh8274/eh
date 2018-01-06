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

    //添加
    @RequestMapping(value = "addMachine", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson addMachine(Machine machine){
        return machineService.addMachine(machine);
    }

    //主键查询
    @RequestMapping(value = "findMachine", method = RequestMethod.POST)
    @ResponseBody
    public Machine queryMachine(int id){
        return machineService.findMachine(id);
    }

    //删除
    @RequestMapping(value = "deleteMachine", method = RequestMethod.POST)
    public ReturnJson deleteMachine(int id){
        return machineService.deleteMachine(id);
    }

    //修改
    @RequestMapping(value = "updateMachine", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson updateMachine(Machine machine){
        return machineService.updateMachine(machine);
    }

    //分页展示
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

    //批量删除
    @RequestMapping(value = "deleteMachineArr", method = RequestMethod.POST)
    public ReturnJson deleteMachineArr(String ids){
        return machineService.deleteMachineArr(ids);
    }

    //跳转到机器添加页面
    @RequestMapping("toadd")
    public String toadd(){
        return "/machine/addMachine";
    }

}
