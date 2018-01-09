package com.ycb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.AmountMapper;
import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.service.AmountService;
import com.ycb.util.PageUtil;

@Service
public class AmountServiceImpl implements AmountService{
	
	@Autowired
	private AmountMapper amountMapper;
	
	
	@Override
	public Amount queryMyAmount(String userID) {
		// TODO Auto-generated method stub
		return amountMapper.queryMyAmount(userID);
	}


	@Override
	public PageUtil<Machine> queryAllAmount(PageUtil<Machine> pageUtil) {
		 	List<Machine> list =  amountMapper.queryAllAmount(pageUtil);
	        Integer totalCount=amountMapper.selectCount(pageUtil);
	        pageUtil.setTotalCount(totalCount);
	        pageUtil.setList(list);
	        return pageUtil;
	}
	
}	
