package com.ycb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SyStemMapper;
import com.ycb.entity.SysUser;
import com.ycb.service.TreeService;

@Service
public class TreeServiceImpl implements TreeService {

	@Autowired
	private SyStemMapper  syStemMapper;

	public SysUser selectUserId(String id) {
		return syStemMapper.selectSysUserByloginName(id);
	}
}
