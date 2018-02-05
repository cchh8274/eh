package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycb.dao.UnitMapper;
import com.ycb.entity.Unit;
import com.ycb.service.UnitService;
@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitMapper  unitMapper;
	

	@Override
	public List<Unit> selectUnitList(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<JSONObject> selectUnitbyArea(String name) {
		List<Unit> list = unitMapper.selectByAreaName(name);
		List<JSONObject> data=new ArrayList<JSONObject>();
		for (Unit item : list) {
			System.out.println(item.getLocation());
			JSONObject itemjb=JSON.parseObject(item.getLocation());
			data.add(itemjb);
		}
		return data;
	}

}
