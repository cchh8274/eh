package com.ycb.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.xbase.frame.util.DateUtils;

import com.ycb.service.MachineService;
import com.ycb.service.WxOrderService;
import com.ycb.util.IDGeneratorTools;

/***
 * 每个月14号晚上进行[当月] 的收益汇总求和 千万不要累加前一个月的数据
 * 账户求和
 * @author chenghui
 *
 */
@Service
public class SyncAmountAEarninsGatherJob {
	private static final Log LOGGER = LogFactory
			.getLog(SyncAmountAEarninsGatherJob.class);

	@Autowired
	private WxOrderService wxOrderService;

	@Autowired
	private MachineService machineService;

	public void jobs() {
		try {
			LOGGER.info("SyncCoffeManicheJob.Jobs--从订单表中提取每个咖啡机数量开始");
			List<HashMap> list = wxOrderService.queryUserManinche();
			if (CollectionUtils.isEmpty(list)) {
				return;
			}
			List<HashMap> data = new ArrayList<HashMap>(list.size());
			for (HashMap hashMap : list) {
				hashMap.put("id", IDGeneratorTools.createId());
				hashMap.put("openid", hashMap.get("openid"));
				hashMap.put("wxUserName", "");
				hashMap.put("areaId", hashMap.get("areaId"));
				hashMap.put("unversityName", "");
				hashMap.put("unversityId", hashMap.get("unversityId"));
				hashMap.put("number", hashMap.get("number"));
				hashMap.put("payTime", hashMap.get("payTime"));
				hashMap.put("createTime", DateUtils.getCurrDate());
				hashMap.put("createUser", "system");
				data.add(hashMap);
			}
			machineService.insertCoffee(data);
			LOGGER.info("SyncCoffeManicheJob.Jobs--从订单表中提取每个咖啡机数量完成");
		} catch (Exception e) {
			LOGGER.info("SyncCoffeManicheJob.Jobs--从订单表中提取每个咖啡机数量异常");
			LOGGER.error("SyncCoffeManicheJob.Jobs--从订单表中提取每个咖啡机数量异常,key:"+e.getMessage(),e);

		}
	}
}
