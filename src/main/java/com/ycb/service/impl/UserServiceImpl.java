package com.ycb.service.impl;

import java.util.Date;
import java.util.UUID;

import org.jk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SyStemMapper;
import com.ycb.entity.SysUser;
import com.ycb.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SyStemMapper syStemMapper;

	public SysUser checkSysUserLogin(SysUser user) {
		return syStemMapper.selectSysUserByloginName(user.getLoginname());
	}

	public void saveSysUser(SysUser user) {
		// 随机数id
		user.setId(UUID.randomUUID().toString());
		// 密码加密
		user.setPwd(MD5Util.md5(user.getPwd()));

		// 创建时间
		user.setCreatedatetime(new Date());
		// 修改时间
		user.setUpdatedatetime(new Date());
		syStemMapper.insertSysUser(user);
	}
}
