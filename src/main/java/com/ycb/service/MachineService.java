package com.ycb.service;

import java.util.List;

import com.ycb.entity.Amount;
import com.ycb.entity.Machine;
import com.ycb.entity.Unit;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;

/**
 * Created by zhm on 2018/1/3.
 */
public interface MachineService {

	Integer queryMachineCount(Integer i);

	Integer queryMachineCountMonth(Integer i);


	List<Machine> queryUnitMat(String name);

	String queryMachine(String code);
}
