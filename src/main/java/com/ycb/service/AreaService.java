package com.ycb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ycb.entity.Area;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

public interface AreaService {

	PageUtil<Area> findAreaList(PageUtil<Area> pageUtil);

	ReturnJson addArea(Area area);

	ReturnJson deleteAreaArr(String ids);

	String selectArea ()throws UnsupportedEncodingException ;

}
