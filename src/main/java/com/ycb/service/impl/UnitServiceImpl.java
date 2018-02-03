package com.ycb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Unit> selectUnitbyArea(String name) {
		List<Unit> li2 = unitMapper.selectByAreaName(name);
		return li2;
	}

}
