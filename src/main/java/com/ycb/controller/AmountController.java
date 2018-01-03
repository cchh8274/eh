package com.ycb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycb.entity.Amount;
import com.ycb.service.AmountService;
/**
 * 账户管理
 * 查询我的账户
 * 我的余额
 * 所有账户的余额
 * @author 
 *
 */
@Controller
@RequestMapping("/amount")
public class AmountController {
	
	@Autowired
	private AmountService amountService;
	
	
	public  Amount  queryAmount() {
		return null;
	}
	
}
