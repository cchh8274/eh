package com.ycb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统用户管理
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

	@RequestMapping("/toUser")
	public String toUser() {
		return "/user/user";
	}
	
}
