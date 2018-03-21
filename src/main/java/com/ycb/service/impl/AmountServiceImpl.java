package com.ycb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.AmountMapper;
import com.ycb.dao.TotalAmountMapper;
import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.entity.TotalAmount;
import com.ycb.service.AmountService;
import com.ycb.util.PageUtil;

@Service
public class AmountServiceImpl implements AmountService {

	@Autowired
	private AmountMapper amountMapper;
	@Autowired
	private TotalAmountMapper totalAmountMapper;

	@Override
	public Amount queryMyAmount(String userID) {
		// TODO Auto-generated method stub
		return amountMapper.queryMyAmount(userID);
	}

	@Override
	public PageUtil<Machine> queryAllAmount(PageUtil<Machine> pageUtil) {
		List<Machine> list = amountMapper.queryAllAmount(pageUtil);
		Integer totalCount = amountMapper.selectCount(pageUtil);
		pageUtil.setTotalCount(totalCount);
		pageUtil.setList(list);
		return pageUtil;
	}

	@Override
	public void addTotalMoney(TotalAmount ta) {
		TotalAmount am=totalAmountMapper.selectAmonut(ta.getOpenid());
		if(am==null){
			ta.setCreatetime(new Date());
			totalAmountMapper.insert(ta);
		}
	}

	@Override
	public TotalAmount queryamount(String openid) {
		TotalAmount am=totalAmountMapper.selectAmonut(openid);
		return am;
	}

}
