package com.ycb.service;

import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.util.PageUtil;

public interface AmountService {

	Amount queryMyAmount(String userID);

	PageUtil<Machine> queryAllAmount(PageUtil<Machine> pageUtil);

}
