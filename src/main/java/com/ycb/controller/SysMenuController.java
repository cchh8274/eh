package com.ycb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.model.MenuInfo;
import com.ycb.service.TblMenuService;

@Controller
@RequestMapping("/menu")
public class SysMenuController {
	@Autowired
	private TblMenuService tblMenuService;
	
	@RequestMapping("/queryall")
	@ResponseBody
	public  List<MenuInfo>  queryMenuAll(){
		return tblMenuService.queryMenuAll();
	}
}
