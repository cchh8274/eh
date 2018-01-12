package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.SysRole;
import com.ycb.service.SysRoleService;
import com.ycb.util.PageUtil;

@Controller
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService roleService;

	/**
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页条数
	 * @return 分页Role信息
	 */
	@RequestMapping(value = "selectRoleList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectRoleList(Integer page, Integer rows,
			PageUtil<SysRole> rolePage) {
		rolePage.setCpage(page);
		rolePage.setPageSize(rows);
		rolePage = roleService.selectRoleList(rolePage);
		Map<String, Object> map = new HashMap<>();
		map.put("total", rolePage.getTotalCount());
		map.put("rows", rolePage.getList());
		return map;
	}

	@RequestMapping("toRoleList")
	public String toRoleList() {
		return "role/roleList";
	}

}
