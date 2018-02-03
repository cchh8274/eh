package com.ycb.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycb.service.WxOrderService;
/**
 * 咖啡机管理
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/coffee")
public class CoffeeContoller {
	
	
	private static final Logger logger=Logger.getLogger(CoffeeContoller.class);
	
	@Autowired
	private  WxOrderService wxOrderService;
	
	
	@RequestMapping("/getMachine")
	@ResponseBody
	public String queryMachinebyOpenid(String openID){
		logger.info("查询咖啡机的信息的OpenID=》"+openID);
		try {
			return  JSON.toJSONString(wxOrderService.queryMachinebyOpenid(openID));
		} catch (Exception e) {
			logger.error("查询咖啡机信息异常!");
			logger.error(e.getMessage(),e);
			return null;
		}
	}
 	
}
