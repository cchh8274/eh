package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kanmars.ecm.dao.TblAmountInfoMapper;
import cn.kanmars.ecm.dao.TblOrderDealMapper;
import cn.kanmars.ecm.entity.TblOrderDeal;

import com.ycb.service.AmountService;
import com.ycb.util.Constants;

@Service
public class AmountServiceImpl implements AmountService {

	@Autowired
	private TotalAmountMapper totalAmountMapper;
	@Autowired
	private TblOrderDealMapper  tblOrderDealMapper;
	@Autowired
	private TblAmountInfoMapper tblAmountInfoMapper; 
	/**
	 * 查询账户余额
	 */
	@Override
	public String queryamount(String openid) {
		return null;
	}

	
	
	@Override
	public List<String> queryAccount(String yesterday) {
		TblOrderDeal deal=new TblOrderDeal();
		deal.setCreateTime(yesterday);
		deal.setPayStatus(Constants.PAY_STATUS_SUCCESS);
		List<TblOrderDeal> list=tblOrderDealMapper.selectList(deal);
		Set<String> openids=new HashSet<>();
		for (TblOrderDeal deals : list) {
			openids.add(deals.getOpenid());
		}
		List<String>  ops=new ArrayList<String>(openids.size());
		for (String string : openids) {
			ops.add(string);
		}
		return ops;
	}


	@Override
	public void insertAccount(List<HashMap> batchlist) throws Exception{
		tblAmountInfoMapper.insertBatch(batchlist);
	}

}
