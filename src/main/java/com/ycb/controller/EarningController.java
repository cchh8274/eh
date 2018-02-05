package com.ycb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ycb.service.EarningService;
/**
 * 收益管理
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/earning")
public class EarningController {
	@Autowired
	private EarningService earningService; 
	
	@RequestMapping(value={"getEarningMoney"},method=RequestMethod.POST)
	public  String  queryEarningMoney(String openID){
		return earningService.queryEarningMoney(openID);
	}
}
