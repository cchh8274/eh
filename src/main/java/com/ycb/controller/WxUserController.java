package com.ycb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.service.WxUserService;
/**
 * 微信用户管理
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/wx")
public class WxUserController {
	@Autowired
	private WxUserService  wxUserService;
	
	@ResponseBody
	@RequestMapping("/getUserInfo")
	public String  getUserInfo(String openID){
		try {
			return wxUserService.getUserInfo(openID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
