package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;
import cn.kanmars.ecm.dao.TblMachineGatherInfoMapper;
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
	@Autowired
	private TblMachineInfoMapper tblMachineInfoMapper;
	@Autowired
	private TblMachineGatherInfoMapper tblMachineGatherInfoMapper;
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


	@Override
	public void insertCoffee(List<HashMap> data) throws Exception{
		List<HashMap> insertList=new ArrayList<HashMap>();
		List<HashMap> updateList=new ArrayList<HashMap>();
		HashMap  item=new HashMap<>();
		for (HashMap map : data) {
			item.put("openid", map.get("openid"));
			item.put("areaId", map.get("areaId"));
			item.put("unversityId", map.get("unversityId"));
			HashMap update=tblMachineGatherInfoMapper.queryOneMap(item);
			if(update!=null){
				HashMap  updateI=new HashMap<>();
				updateI.put("id", update.get("id"));
				updateI.put("number_new", map.get("number"));
				updateI.put("updateTime_new", DateUtils.getCurrDateTime());
				updateList.add(update);
			}else{
				insertList.add(map);
			}
		}
		tblMachineGatherInfoMapper.updateBatch(updateList);
		tblMachineGatherInfoMapper.insertBatch(insertList);
	}



}
