package com.ycb.service;

import java.util.HashMap;
import java.util.List;


/**
 * Created by zhm on 2018/1/3.
 */
public interface MachineService {

	String queryMachine(String code);

	void insertCoffee(List<HashMap> data) throws Exception;
}
