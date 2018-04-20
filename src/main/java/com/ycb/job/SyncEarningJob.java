package com.ycb.job;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;

import com.ycb.service.EarningService;
import com.ycb.service.MachineService;
import com.ycb.service.WxOrderService;
/**
 * 同步每个人的收益数据
 * 1.收益数据从订单表同步过去
 * 2.收益日扫描
 * 3.原来已经开始产生收益的直接插表
 * 4.扫描当前日-1月 的支付时间的订单插入
 * 5.每个月的前15天执行
 * @author chenghui
 *
 */
@Service
public class SyncEarningJob {
	private static final Log LOGGER = LogFactory.getLog(SyncEarningJob.class);

	@Autowired
	private WxOrderService wxOrderService;

	@Autowired
	private MachineService machineService;
	@Autowired
	private EarningService earningService;

	public void jobs() {
		try {
			String now=DateUtils.getCurrDateTime();
			String logkey=now;
			LOGGER.info("SyncEarningJob.Jobs--同步明细收益开始,logkey:"+logkey);
			LOGGER.info("SyncEarningJob.Jobs--同步已有明细收益开始,logkey:"+logkey);
			/** 1.同步原始有的数据 */
			earningService.queryNoSyncData();
			LOGGER.info("SyncEarningJob.Jobs--同步已有明细收益结束,logkey:"+logkey);
			LOGGER.info("SyncEarningJob.Jobs--同步未有收益数据明细收益开始,logkey:"+logkey);
//			earningService.syncOrderEarnings();
			LOGGER.info("SyncEarningJob.Jobs--同步未有收益数据明细收益完成,logkey:"+logkey);
		} catch (Exception e) {
			LOGGER.info("SyncEarningJob.Jobs--同步明细收益异常");
			LOGGER.error("SyncEarningJob.Jobs--同步明细收益异常,key:"+e.getMessage(),e);

		}
	}
}
