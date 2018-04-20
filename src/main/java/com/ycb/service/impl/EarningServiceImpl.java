package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.net.aso.q;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;
import cn.kanmars.ecm.dao.TblEarningsInfoMapper;

import com.ycb.service.EarningService;
import com.ycb.util.Constants;

@Service
public class EarningServiceImpl implements EarningService {
	@Autowired
	private WxEarningsMapper wxEarningsMapper;

	@Autowired
	private TblEarningsInfoMapper tblEarningsInfoMapper;

	@Override
	public String queryEarningMoney(String openID) {

		return null;
	}

	@Override
	public void queryNoSyncData() throws Exception {
		String now=DateUtils.getCurrMonth();
		String queryMonth=DateUtils.countMonth(now,-1)+Constants.EARNINGS_MONTH;
		HashMap  queryMap=new HashMap();
		queryMap.put("earingMonth", queryMonth);
		//查询上一个月的数据
		List<HashMap> list=tblEarningsInfoMapper.queryListMap(queryMap);
		List<HashMap> inserts=new ArrayList<HashMap>(list.size());
		for (HashMap hashMap : list) {
			hashMap.put("earingMonth", now+Constants.EARNINGS_MONTH);
			hashMap.put("earningStartTime", DateUtils.getCurrDate());
			hashMap.put("earningSendTime", DateUtils.getCurrDate());
			hashMap.put("createTime", DateUtils.getCurrDate());
			hashMap.put("createUser", "system");
			inserts.add(hashMap);
		}
		tblEarningsInfoMapper.insertBatch(inserts);
	}
}
