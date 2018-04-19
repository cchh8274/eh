package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.xbase.frame.util.DateUtils;
import cn.kanmars.ecm.dao.TblBankamountInfoMapper;
import cn.kanmars.ecm.entity.TblBankamountInfo;

import com.alibaba.fastjson.JSON;
import com.ycb.exception.BaseException;
import com.ycb.service.BankService;
@Service
public class BankServceiImpl implements BankService {
	
	@Autowired
	private TblBankamountInfoMapper tblBankamountInfoMapper;
	
	/**
	 * 绑定银行卡
	 * @throws BaseException 
	 */
	@Override
	public int addBankAmount(TblBankamountInfo bankAmount) throws BaseException {
		if(!validPossession(bankAmount.getBankAmountNo())){
			throw new BaseException("该银行卡已经绑定过");
		}
		bankAmount.setCreateTime(DateUtils.getCurrDate());
		bankAmount.setCreateUser("system");
		int i= tblBankamountInfoMapper.insert(bankAmount);
		return i;
	}
	
	private  boolean  validPossession(String bankCard){
		TblBankamountInfo bankAmount=new TblBankamountInfo();
		bankAmount.setBankAmountNo(bankCard);
		bankAmount=tblBankamountInfoMapper.select(bankAmount);
		if(bankAmount == null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询银行卡
	 */
	@Override
	public String queryBankAmount(String openid) {
		TblBankamountInfo bankAmount=new TblBankamountInfo();
		bankAmount.setOpenid(openid);
		List<TblBankamountInfo> list=tblBankamountInfoMapper.selectList(bankAmount);
		if(CollectionUtils.isEmpty(list)){
			return "没有绑定银行卡";
		}
		List<Map<String, String>>  infos=new ArrayList<Map<String,String>>();
		for (TblBankamountInfo tblBankamountInfo : list) {
			Map<String,String>  bankAmountinfo=new HashMap<>();
			bankAmountinfo.put("bankname", tblBankamountInfo.getBankName());
			bankAmountinfo.put("bankcard", tblBankamountInfo.getBankAmountNo());
			infos.add(bankAmountinfo);
		}
		return JSON.toJSONString(infos);
	}
	


	
}
