package com.ycb.job;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ycb.service.WxOrderService;

import cn.com.xbase.frame.util.DateUtils;

/**
 * 订单表同步
 * 
 * @author chenghui 1.查询临时表 2.补充信息 3.同步订单信息
 */
@Service
public class SyncPayOrderInfoJob {

	private static final Log LOGGER = LogFactory
			.getLog(SyncPayOrderInfoJob.class);

	@Autowired
	private WxOrderService orderService;

	public void jobs() {
		try {
			String logkey = DateUtils.getCurrDate();
			LOGGER.info("SyncPayOrderInfoJob.jobs---同步订单数据开始,logkey:" + logkey);
			String now = DateUtils.getCurrDate();
			String yesterday = DateUtils.countDate(now, -1);
			LOGGER.info("SyncPayOrderInfoJob.jobs---同步订单时间为"+yesterday+",logkey:"+logkey);
			List<HashMap> list = orderService.queryDealList(yesterday);
			LOGGER.info("SyncPayOrderInfoJob.jobs---同步订单数据量为"+list.size()+",logkey:"+logkey);
			if (CollectionUtils.isEmpty(list)) {
				return;
			}
			for (HashMap hashMap : list) {
				orderService.queryBatisinfo(hashMap);
				hashMap.put("updateTime", DateUtils.getCurrDate());
				hashMap.put("updateUser", "SyncPayOrderInfoJob");
			}
			orderService.insertBatch(list);
			LOGGER.info("SyncPayOrderInfoJob.jobs---同步订单数据完成,logkey:" + logkey);
		} catch (Exception e) {
			LOGGER.error("SyncPayOrderInfoJob.jobs---同步订单数据完成,logkey:" + e.getMessage(),e);
		}
	}

}
