package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycb.service.AmountService;

/**
 * 账户管理
 * 查询我的账户 我的余额 所有账户的余额
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/amount")
public class AmountController extends BaseController{
	
	private static final Log LOGGER=LogFactory.getLog(AmountController.class);
	
	@Autowired
	private AmountService amountService;

	/**
	 * 通过openid 查询账户总额
	 * @param openid
	 * @return
	 */
	@RequestMapping("/queryUserAmount")
	@ResponseBody
	public String queryamount(String openid) {
		try {
			if(StringUtils.isEmpty(openid)){
				return this.toJSONString("error", "openid不能为空!");
			}
			String totalMoney = amountService.queryamount(openid);
			return this.toJSONString(totalMoney);
		} catch (Exception e) {
			LOGGER.error("AmountController.queryamount--查询账户余额异常,openid:"+openid+","+e.getMessage(),e);
			return this.toJSONString("error", "系统异常!");
		}
	}

	

}
