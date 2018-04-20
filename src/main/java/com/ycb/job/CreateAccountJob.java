package com.ycb.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;

import com.ycb.service.AccountService;
import com.ycb.util.Constants;
import com.ycb.util.IDGeneratorTools;

/**
 * 微信购买用户创建虚拟账户job 
 * 1.查询订单表支付成功的订单获取openid 
 * 2.通过openid 创建用户虚拟账户
 * 3.每次查询只查询支付时间在前一天的数据 ,减少数据库压力
 * 4.每天凌晨检查, 用户购买完成之后第二天创建账户
 * 
 * @author chenghui
 *
 */
@Service
public class CreateAccountJob {
	private static final Log LOGGER = LogFactory
			.getLog(CreateAccountJob.class);
	
	@Autowired
	private AccountService accountService;
	
	public void jobs() {
		try {
		String now=DateUtils.getCurrDate();
		LOGGER.info("CreateAccountJob.jobs--创建虚拟账户开始,logkey:"+now);
		String yesterday=DateUtils.countDate(now, -1);
		LOGGER.info("CreateAccountJob.jobs--创建虚拟账户时间为"+yesterday+",logkey:"+now);
		List<String> list=accountService.queryAccount(yesterday);
		LOGGER.info("CreateAccountJob.jobs--创建虚拟账户数量为"+list.size()+",logkey:"+now);
		List<HashMap> batchlist=new ArrayList<HashMap>(list.size());
		for (String openid : list) {
			HashMap<String,String> data=new HashMap<>();
			data.put("id",IDGeneratorTools.createId());
			data.put("openid",openid);
			data.put("userName","");
			data.put("accountMoney","0");
			data.put("isStart",Constants.FREEZE_STATUS_OK);
			data.put("accountId",IDGeneratorTools.createId());
			data.put("accountTime",DateUtils.getCurrDateTime());
			data.put("createTime",DateUtils.getCurrDate());
			data.put("createUser","system");
			data.put("isFreeze",Constants.FREEZE_STATUS_OK);
			batchlist.add(data);
		}
		accountService.insertAccount(batchlist);
		LOGGER.info("CreateAccountJob.jobs--创建虚拟账户完成,logkey:"+now);
		} catch (Exception e) {
			LOGGER.info("CreateAccountJob.jobs--创建虚拟账户异常,logkey:"+DateUtils.getCurrDate());
			LOGGER.error("CreateAccountJob.jobs--创建虚拟账户异常"+e.getMessage(),e);
		}
	}
}
