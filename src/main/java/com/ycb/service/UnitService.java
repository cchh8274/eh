package com.ycb.service;

import java.util.List;

import com.ycb.entity.Unit;

public interface UnitService {

	List<Unit> selectUnitList(String name);

	List<Unit> selectUnitbyArea(String name);

}
