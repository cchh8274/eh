package com.ycb.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ConfigController {
	@RequestMapping("/config")
	@ResponseBody
	public Map<String, String> config(String url) {
		logger.info("获取配置信息");
		return wxPayService.config(url);
	}
}
