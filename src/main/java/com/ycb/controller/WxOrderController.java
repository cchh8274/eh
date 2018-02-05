package com.ycb.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.service.WxOrderService;

@Controller
@RequestMapping("/wxorder")
public class WxOrderController {
	
	private static final Logger logger=Logger.getLogger(WxOrderController.class);
	@Autowired
	private WxOrderService wxOrderService;
	
	@RequestMapping("/towxorder")
	public String toPage(){
		return "/wxorder/wxorder";
	}
	
	@RequestMapping("/queryall")
	@ResponseBody
	public Map<String,Object>  queryWxOrderAll(Integer rows,Integer page,Map<String,Object> map){
		map.put("pagesize", rows);
		map.put("page", page);
		wxOrderService.queryWxOrderAll(map);
		return map;
	}
}
