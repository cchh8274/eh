package com.ycb.service.impl;

import com.ycb.dao.MachineMapper;
import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.entity.Unit;
import com.ycb.service.MachineService;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhm on 2018/1/3.
 */
@Service
public class MachineServiceImp implements MachineService{


    @Autowired
    private MachineMapper machineMapper;

    @Override
    public ReturnJson addMachine(Machine machine) {

        ReturnJson rj = new ReturnJson();

        if(machine == null){

        }
        int i =  machineMapper.insert(machine);
        if (i>0){
            rj.setMsg("添加机器成功!");
            rj.setSuccess(true);
        }else{
            rj.setSuccess(false);
            rj.setMsg("添加机器成功，请联系管理员!");
        }
        return rj;
    }

    @Override
    public Machine findMachine(int id) {
        if("".equals(id)){

        }
        Machine machine = machineMapper.selectByPrimaryKey(id);

        return machine;
    }

    @Override
    public ReturnJson deleteMachine(int id) {

        ReturnJson rj = new ReturnJson();

        int i = machineMapper.deleteByPrimaryKey(id);

        if (i>0){
            rj.setMsg("修改成功");
            rj.setSuccess(true);
        }else{
            rj.setSuccess(false);
            rj.setMsg("修改失败");
        }
        return rj;
    }

    @Override
    public ReturnJson updateMachine(Machine machine) {

        ReturnJson rj = new ReturnJson();

        int i = machineMapper.updateByPrimaryKey(machine);

        if (i>0){
            rj.setMsg("修改成功");
            rj.setSuccess(true);
        }else{
            rj.setSuccess(false);
            rj.setMsg("修改失败");
        }
        return rj;
    }

    @Override
    public PageUtil<Machine> findMachineList(PageUtil<Machine> pageUtil) {
        List<Machine> list =  machineMapper.selectMachineList(pageUtil);
        Integer totalCount=machineMapper.selectCount(pageUtil);
        pageUtil.setTotalCount(totalCount);
        pageUtil.setList(list);
        return pageUtil;
    }

    @Override
    public ReturnJson deleteMachineArr(String ids) {
        ReturnJson rj = new ReturnJson();
        String[] split = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            list.add(Integer.valueOf(split[i]));
        }
        int j = machineMapper.deleteMachineArr(list);
        if (j>0){
            rj.setMsg("删除成功");
            rj.setSuccess(true);
        }else{
            rj.setSuccess(false);
            rj.setMsg("删除失败");
        }
        return rj;
    }

	@Override
	public Integer queryMachineCount(Integer i) {
		return	machineMapper.queryMachineCount(i);
	}

	@Override
	public Integer queryMachineCountMonth(Integer i) {
		// TODO Auto-generated method stub
		return machineMapper.queryMachineMonthCount(i);
	}

	@Override
	public List<Unit> selectUnit() {
		// TODO Auto-generated method stub
		return machineMapper.selectUnit();
	}

	@Override
	public List<Machine> queryUnitMat(String name) {
		return machineMapper.queryUnitMat(name);
	}
}
