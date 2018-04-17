package com.ycb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.xbase.frame.util.DateUtils;

import com.ycb.service.AreaService;

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

	private static final Log LOGGER = LogFactory.getLog(AreaController.class);

	/**
	 * 查询所有的区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectArea", method = RequestMethod.POST)
	@ResponseBody
	public String selectArea() {
		try {
			LOGGER.info("AreaController.queryArea--查询的到的区域列表时间为"+DateUtils.getCurrDate());
			String infos = areaService.selectArea();
			LOGGER.info("AreaController.queryArea--查询的到的区域列表为" + infos);
			return infos;
		} catch (Exception e) {
			LOGGER.info("AreaController.queryArea--查询的到的区域列表出现异常");
			LOGGER.error(e.getMessage(),e);
			return null;
		}
	}

}
