package com.ycb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.service.AmountService;
import com.ycb.util.DataGridJson;
import com.ycb.util.PageUtil;
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
	
	@RequestMapping("/queryAmount")
	@ResponseBody
	public DataGridJson  queryAllAmount(PageUtil<Machine> pageUtil, Integer rows, Integer page) {
		 pageUtil.setCpage(page);
	        pageUtil.setPageSize(rows);
	        pageUtil = amountService.queryAllAmount(pageUtil);
	        DataGridJson dj = new DataGridJson();
	        dj.setTotal(pageUtil.getTotalCount());
	        dj.setRows(pageUtil.getList());
	        return dj;
	}
	
	
	@RequestMapping("/queryMyAmount")
	@ResponseBody
	public  Amount  queryMyAmount(String userID) {
		return amountService.queryMyAmount(userID);
	}
	
	
	
	
}
