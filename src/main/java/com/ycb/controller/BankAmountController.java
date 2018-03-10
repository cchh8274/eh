package com.ycb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycb.entity.BankAmount;
import com.ycb.service.BankService;

/**
 * 银行账户
 * 
 * @author chenghui
 *
 */
@RequestMapping("/bankAmount")
@Controller
public class BankAmountController {
	@Autowired
	private BankService bankService;
	
	@RequestMapping("/addBankAmount")
	@ResponseBody
	public String addBankAmount(BankAmount bankAmount) {
		try {
			bankService.addBankAmount(bankAmount);
			Map<String,String> map=new HashMap<>();
			map.put("result", "添加银行卡成功");
			return JSON.toJSONString(map);
		} catch (Exception e) {
			// TODO: handle exception
			return "添加失败";
		}
	}
	
	@RequestMapping("/searchBankAmount")
	@ResponseBody
	public String searchBankAmount(String  openID) {
		List<BankAmount> list=bankService.searchBankAmount(openID);
		return JSON.toJSONString(list);
	}
}
