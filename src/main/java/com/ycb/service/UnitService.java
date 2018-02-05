package com.ycb.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ycb.entity.Unit;

public interface UnitService {

	List<Unit> selectUnitList(String name);

	List<JSONObject> selectUnitbyArea(String name);

}
