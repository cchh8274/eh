package com.ycb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycb.entity.Unit;
import com.ycb.service.UnitService;

@Controller
@RequestMapping("/unit")
public class UnitController {
	@Autowired
	private UnitService unitService;

	// 跳转到区域页面
	@RequestMapping("toUnit")
	public String toArea() {
		return "/unit/showUnit";
	}


	// 查询所有的区域
	@RequestMapping(value = "selectUnitList", method = RequestMethod.POST)
	@ResponseBody
	public List<Unit> selectUnitList() {
		return unitService.selectUnitList("");
	}
	
	@RequestMapping(value = "/selectUnitbyArea", method = RequestMethod.POST)
	@ResponseBody
	public String selectUnitbyArea(String name){
		return JSON.toJSONString(unitService.selectUnitbyArea(name));
	}
}
