package com.ycb.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.ReflectMapper;
import com.ycb.dao.TotalAmountMapper;
import com.ycb.entity.Reflect;
import com.ycb.entity.TotalAmount;
import com.ycb.service.ReflectService;
import com.ycb.util.IDGeneratorTools;
@Service
public class ReflectServiceImpl implements ReflectService {
	@Autowired	
	private ReflectMapper reflectMapper;
	@Autowired
	private TotalAmountMapper  totalAmountMapper;

	@Override
	public String reflectMoney(Reflect reflect) {
		reflect.setCreatetime(new Date());
		reflect.setOrderno(IDGeneratorTools.createId());
		reflectMapper.insert(reflect);
		TotalAmount tmoney=totalAmountMapper.selectAmonut(reflect.getOpenid());
		BigDecimal lmoney=new BigDecimal(tmoney.getMoney()).subtract(new BigDecimal(reflect.getMoney()));
		TotalAmount tas=new TotalAmount();
		tas.setId(tmoney.getId());
		tas.setMoney(Integer.valueOf(lmoney.toString()));
		totalAmountMapper.updateByPrimaryKeySelective(tas);
		return lmoney.toString();
	}
}
