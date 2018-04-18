package com.ycb.service.impl;

import cn.kanmars.ecm.dao.TblMachineInfoMapper;
import cn.kanmars.ecm.entity.TblMachineInfo;

import com.alibaba.fastjson.JSON;
import com.ycb.service.MachineService;
import com.ycb.util.PageUtil;

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
    private TblMachineInfoMapper tblMachineInfoMapper;



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

	@Override
	public String queryMachine(String code) {
		TblMachineInfo  info=new TblMachineInfo();
		info.setAdress(code);
		return JSON.toJSONString(tblMachineInfoMapper.selectList(info));
	}
}
