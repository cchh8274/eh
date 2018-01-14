package com.ycb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.Area;
import com.ycb.service.AreaService;
import com.ycb.util.DataGridJson;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

/**
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService areaService;

	// 跳转到区域页面
	@RequestMapping("toArea")
	public String toArea() {
		return "/area/showArea";
	}

	// 分页展示
	@RequestMapping(value = "findAreaList", method = RequestMethod.POST)
	@ResponseBody
	public DataGridJson queryAreaList(PageUtil<Area> pageUtil, Integer rows,
			Integer page) {
		pageUtil.setCpage(page);
		pageUtil.setPageSize(rows);
		pageUtil = areaService.findAreaList(pageUtil);
		DataGridJson dj = new DataGridJson();
		dj.setTotal(pageUtil.getTotalCount());
		dj.setRows(pageUtil.getList());
		return dj;
	}

	// 添加
	@RequestMapping(value = "addArea", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson addArea(Area area) {
		return areaService.addArea(area);
	}

	// 批量删除
	@RequestMapping(value = "deleteAreaArr", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson deleteMachineArr(String ids) {
		return areaService.deleteAreaArr(ids);
	}

	// 查询所有的区域
	@RequestMapping(value = "selectArea", method = RequestMethod.POST)
	@ResponseBody
	public String selectArea() {
		try {
			return areaService.selectArea();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
