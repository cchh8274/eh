package com.ycb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ycb.entity.SysUser;
import com.ycb.entity.business.SessionInfo;
import com.ycb.service.TreeService;
import com.ycb.util.ConfigUtil;

@Controller
public class TreeController {

	@Autowired 
	private TreeService treeService;
	
	public void selectUserId(HttpServletRequest request){
		SessionInfo sf = (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		SysUser user=treeService.selectUserId(sf.getUser().getId());
	}
}
