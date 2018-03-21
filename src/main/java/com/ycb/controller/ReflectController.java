package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycb.entity.Reflect;
import com.ycb.service.ReflectService;

@RequestMapping("/reflect")
@Controller
public class ReflectController {

	@Autowired
	private ReflectService reflectService;

	@RequestMapping("/reflectMoney")
	@ResponseBody
	public String reflectMoney(Reflect reflect) {
		Map<String, String> map = new HashMap<>();
		try {
			String lm = reflectService.reflectMoney(reflect);
			map.put("result", lm);
			return JSON.toJSONString(map);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			map.put("result", "添加失败");
			return JSON.toJSONString(map);
		}
	}
}
