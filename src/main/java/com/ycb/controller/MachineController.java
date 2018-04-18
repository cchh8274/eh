package com.ycb.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.com.xbase.frame.util.DateUtils;

import com.ycb.service.MachineService;
import com.ycb.util.DataGridJson;
import com.ycb.util.PageUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhm on 2018/1/3.
 */
@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController{
	
	private static final Log LOGGER = LogFactory.getLog(MachineController.class);

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "queryMachine", method = RequestMethod.POST)
	@ResponseBody
	public String queryMachine(String code) {
		try {
			LOGGER.info("MachineController.queryMachine--查询的到的区域列表时间为"+ DateUtils.getCurrDate());
			String infos = machineService.queryMachine();
			LOGGER.info("MachineController.queryMachine--查询的到的区域列表为" + infos);
			return this.toJSONString(infos);
		} catch (Exception e) {
			LOGGER.info("AreaController.queryArea--查询的到的区域列表出现异常");
			LOGGER.error(e.getMessage(), e);
			return this.toJSONString("系统异常!");
		}
	}




    
    //查询日销量
    @RequestMapping(value = "queryMachineCount", method = RequestMethod.POST)
    @ResponseBody
    public Integer queryMachineCount(Integer i){
        return machineService.queryMachineCount(i);
    }
    //查询月销量
    @RequestMapping(value = "queryMachineCountMonth", method = RequestMethod.POST)
	@ResponseBody
	public  Integer  queryMyAmount(Integer i) {
		return machineService.queryMachineCountMonth(i);
	}
	
    //查询机器数
    @RequestMapping(value = "queryUnitMat", method = RequestMethod.POST)
	@ResponseBody
	public  List<Machine>  queryUnitMat(String  name,HttpServletResponse response) {
		return machineService.queryUnitMat(name);
	}
    
   
    
    @RequestMapping(value = "selectUnit", method = RequestMethod.POST)
    @ResponseBody
    public List<Unit> selectUnit(){
        return machineService.selectUnit();
    }
    
    
    
}
