package com.ycb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ycb.service.ReflectService;

/**
 * 体现接口 1.用户发起体现 2.检测账户余额
 * 
 * @author chenghui
 *
 */
@RequestMapping("/reflect")
@Controller
public class ReflectController extends BaseController {
	
	private static final Log LOGGER=LogFactory.getLog(ReflectController.class);
	
	@Autowired
	private ReflectService reflectService;

	/**
	 * 用户发起体现
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/reflectMoney")
	@ResponseBody
	public String reflectMoney(String param) {
		LOGGER.info("ReflectController.reflectMoney,开始发起体现,体现的参数为:"+param);
		try {
			if (StringUtils.isEmpty(param)) {
				return this.toJSONString("error", "接口参数不能为空!");
			}
			Map<String,String>  map=new HashMap<String,String>();
			JSONObject jsonObject = JSONObject.parseObject(param);
			if (StringUtils.isEmpty(jsonObject.getString("openid"))) {
				LOGGER.info("ReflectController.reflectMoney,openid不能为空!");
				return this.toJSONString("error", "openid不能为空!");
			}
			map.put("openid", jsonObject.getString("openid"));
			if (StringUtils.isEmpty(jsonObject.getString("money"))) {
				LOGGER.info("ReflectController.reflectMoney,体现金额不能为空!");
				return this.toJSONString("error", "体现金额不能为空!");
			}
			map.put("money", jsonObject.getString("money"));
			if (StringUtils.isEmpty(jsonObject.getString("bankcard"))) {
				LOGGER.info("ReflectController.reflectMoney,银行卡号不能为空!");
				return this.toJSONString("error", "银行卡号不能为空!");
			}
			map.put("bankcard", jsonObject.getString("bankcard"));
			if (StringUtils.isEmpty(reflectService.queryBankAmount(jsonObject.getString("bankcard")))) {
				LOGGER.info("ReflectController.reflectMoney,银行卡号不正确!");
				return this.toJSONString("error", "银行卡号不正确!");
			}
			reflectService.reflectMoney(map);
			LOGGER.info("ReflectController.reflectMoney,openid:"+jsonObject.getString("openid")+",体现成功!");
			return this.toJSONString(map);
		} catch (Exception e) {
			LOGGER.error("ReflectController.reflectMoney体现异常,"+e.getMessage(),e);
			return this.toJSONString("error", "系统异常!");
		}
	}
}
