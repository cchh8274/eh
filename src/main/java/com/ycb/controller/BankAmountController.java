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

import cn.com.xbase.frame.util.DateUtils;
import cn.kanmars.ecm.entity.TblBankamountInfo;

import com.alibaba.fastjson.JSON;
import com.ycb.exception.BaseException;
import com.ycb.service.BankService;

/**
 * 银行账户接口
 *  1. 用户绑定银行卡接口 校验银行卡是否存在 校验基础非空验证信息 保存数据库
 *     1).如果绑定过银行卡，直接带出基本信息
 *  2. 用户查询银行卡接口 通过open_id
 * 查询该用户的所绑定的银行卡
 * 
 * @author chenghui
 *
 */
@RequestMapping("/bankAmount")
@Controller
public class BankAmountController extends BaseController {

	private static Log logger = LogFactory.getLog(BankAmountController.class);

	@Autowired
	private BankService bankService;

	/**
	 * 1.校验数据 2.查询是否绑定过 3.查询银行卡，名称 。姓名是否唯一
	 * 
	 * @param bankAmount
	 * @return
	 */
	@RequestMapping("/addBankAmount")
	@ResponseBody
	public String addBankAmount(TblBankamountInfo bankAmount) {
		Map<String, Object> map = new HashMap<String, Object>();
		String _log_key = DateUtils.getCurrDate();
		map.put("code", "false");
		map.put("logKey", _log_key);
		try {
			map.put("bankAmount", bankAmount);
			if (!validAmount(map)) {
				return this.toJSONString("error", (String) map.get("message"));
			}
			int i = bankService.addBankAmount(bankAmount);
			if (i != 1) {
				throw new BaseException("绑定银行卡失败,重新发起请求");
			}
			return this.toJSONString("保存银行卡成功!");
		} catch (Exception e) {
			logger.info("BankAmountController,添加银行卡异常" + e.getMessage());
			logger.error("BankAmountController,添加银行卡异常" + e.getMessage(), e);
			return this.toJSONString("error","系统异常!");
		}
	}
	
	private boolean validAmount(Map<String, Object> map) {
		TblBankamountInfo bankAmount = (TblBankamountInfo) map
				.get("bankAmount");
		String log_key = (String) map.get("logkey");
		if (bankAmount == null) {
			logger.info("BankAmountController,接受到的数据为空,logkey:" + log_key);
			map.put("message", "传输数据不能为空");
			return false;
		}
		logger.info("BankAmountController,校验数据开始,logkey:" + log_key);
		if (StringUtils.isEmpty(bankAmount.getOpenid())) {
			map.put("message", "openid不能为空");
			return false;
		}
		if (StringUtils.isEmpty(bankAmount.getBankName())) {
			map.put("message", "银行名称不能为空");
			return false;
		}
		if (StringUtils.isEmpty(bankAmount.getBankAmountNo())) {
			map.put("message", "银行卡号不能为空");
			return false;
		}
		if (StringUtils.isEmpty(bankAmount.getBankAdress())) {
			map.put("message", "银行开户地址不能为空");
			return false;
		}
		if (StringUtils.isEmpty(bankAmount.getIdcard())) {
			map.put("message", "身份证号不能为空");
			return false;
		}
		if (StringUtils.isEmpty(bankAmount.getUserName())) {
			map.put("message", "姓名不能为空");
			return false;
		}
		logger.info("BankAmountController,校验数据通过,logkey:" + log_key
				+ ",openid:" + bankAmount.getOpenid());
		return true;
	}

	/**
	 * 查询我的银行卡
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping("/queryBankAmount")
	@ResponseBody
	public String queryBankAmount(String openid) {
		try {
			logger.info("BankAmountController.queryBankAmount查询个人银行账号开始");
			if (StringUtils.isEmpty(openid)) {
				logger.info("BankAmountController.queryBankAmount查询个人银行账号开始,当前OpenID为空");
				return this.toJSONString("error","openid不能为空!");
			}
			logger.info("BankAmountController.queryBankAmount查询个人银行账号开始,当前OpenID为:"
					+ openid);
			String list = bankService.queryBankAmount(openid);
			logger.info("BankAmountController.queryBankAmount查询个人银行账号完成,当前OpenID为:"
					+ openid);
			return JSON.toJSONString(list);
		} catch (Exception e) {
			logger.error("BankAmountController.queryBankAmount查询个人银行账号异常,"+e.getMessage(),e);
			return this.toJSONString("error","系统异常!");
		}
		
	}
}
