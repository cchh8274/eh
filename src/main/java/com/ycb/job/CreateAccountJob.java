package com.ycb.job;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.service.AccountService;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.SnowflakeIdWorker;

import cn.com.xbase.frame.util.DateUtils;

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
	@Autowired
	private AccountService accountService;
	
	public void jobs() {
		String now=DateUtils.getCurrDate();
		String yesterday=DateUtils.countDate(now, -1);
		List<String> list=accountService.queryAccount(yesterday);
		for (String openid : list) {
			HashMap<String,String> data=new HashMap<>();
			data.put("id",IDGeneratorTools.createId());
			data.put("id",openid);
			data.put("id","0");
			data.put("id",IDGeneratorTools.createId());
			data.put("id",IDGeneratorTools.createId());
			data.put("id",IDGeneratorTools.createId());
			data.put("id","");
			data.put("id",DateUtils.getCurrDate());
			data.put("id","0");
			data.put("id","system");
			data.put("id",DateUtils.getCurrDate());
			accountService.insertAccount(data);
		}
		
	}
}
