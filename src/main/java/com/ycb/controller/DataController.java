package com.ycb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ycb.service.DataService;

@Controller
@RequestMapping("data")
public class DataController {
	@Autowired
	private DataService dataService;
	
	@RequestMapping(value = "data", method = RequestMethod.GET)
	public void validUser(HttpServletRequest request,HttpServletResponse response, String state,String code) {
		try {
			dataService.insertDatajia();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("重定向失败");
		}
	}
	
	@RequestMapping(value = "datauser", method = RequestMethod.GET)
	public void datauser(HttpServletRequest request,HttpServletResponse response, String state,String code) {
		try {
			dataService.datauser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("重定向失败");
		}
	}
	
}
