package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(@Param("loginname") String loginname,
			@Param("password") String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", loginname);
		map.put("password", password);
		map.put("code", "false");
		if (!StringUtils.isBlank(loginname) || !StringUtils.isBlank(password)) {
			userService.login(map);
		}
		return map;
	}
}
