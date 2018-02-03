package com.ycb.service.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ycb.dao.AreaMapper;
import com.ycb.dao.UnitMapper;
import com.ycb.entity.Area;
import com.ycb.entity.Unit;
import com.ycb.service.AreaService;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;

	@Autowired
	private UnitMapper unitMapper;

	@Override
	public PageUtil<Area> findAreaList(PageUtil<Area> pageUtil) {
		List<Area> list = areaMapper.selectAreaList(pageUtil);
		Integer totalCount = areaMapper.selectCount(pageUtil);
		pageUtil.setTotalCount(totalCount);
		pageUtil.setList(list);
		return pageUtil;
	}

	@Override
	public ReturnJson addArea(Area area) {
		ReturnJson rj = new ReturnJson();
		if (area == null) {
		}
		int i = areaMapper.insert(area);
		if (i > 0) {
			rj.setMsg("添加区域成功!");
			rj.setSuccess(true);
		} else {
			rj.setSuccess(false);
			rj.setMsg("添加区域失败，请联系管理员!");
		}
		return rj;
	}

	@Override
	public ReturnJson deleteAreaArr(String ids) {
		ReturnJson rj = new ReturnJson();
		String[] split = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.valueOf(split[i]));
		}
		int j = areaMapper.deleteAreaArr(list);
		if (j > 0) {
			rj.setMsg("删除成功");
			rj.setSuccess(true);
		} else {
			rj.setSuccess(false);
			rj.setMsg("删除失败");
		}
		return rj;
	}

	@Override
	public String selectArea() throws UnsupportedEncodingException {
		List<Area> list = areaMapper.selectArea();
		return JSON.toJSONString(list);
	}

}
