package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.kanmars.ecm.dao.TblMachineInfoMapper;
import cn.kanmars.ecm.entity.TblMachineInfo;

import com.alibaba.fastjson.JSON;
import com.ycb.service.MachineService;


/**
 * Created by zhm on 2018/1/3.
 * 
 */
/**
 * 通过区域查询机器数
 * @update chenghui
 *
 */
@Service
public class MachineServiceImp implements MachineService{
	
	private TblMachineInfoMapper tblMachineInfoMapper;
	
	
	@Override
	public String queryMachine(String code) {
		List<HashMap<String,String>> list=new ArrayList<>();
		TblMachineInfo machineInfo=new TblMachineInfo();
		machineInfo.setAdress(code);
		List<TblMachineInfo> data=tblMachineInfoMapper.selectList(machineInfo);
		for (TblMachineInfo tblMachineInfo : data) {
			HashMap<String,String> item=new HashMap<String, String>();
			item.put("machineName", tblMachineInfo.getMachineName());
			item.put("adress", tblMachineInfo.getAdress());
			item.put("price", tblMachineInfo.getPrice());
			item.put("totalPlaces", tblMachineInfo.getTotalPlaces());
			item.put("leftPlace", tblMachineInfo.getLeftPlace());
			item.put("outSalePlace", tblMachineInfo.getOutSalePlace());
			item.put("id", tblMachineInfo.getId());
			list.add(item);
		}
		return JSON.toJSONString(list);
	}



}
