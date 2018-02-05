package com.ycb.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SyStemMapper;
import com.ycb.dao.TblSysUserMapper;
import com.ycb.entity.SysUser;
import com.ycb.entity.TblSysUser;
import com.ycb.service.UserService;
import com.ycb.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TblSysUserMapper tblSysUserMapper;
	
	@Autowired
	private SyStemMapper syStemMapper;

	public SysUser checkSysUserLogin(SysUser user) {
		return syStemMapper.selectSysUserByloginName(user.getLoginname().trim());
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


	@Override
	public void login(Map<String, String> map) {
		TblSysUser user=tblSysUserMapper.login(map);
		if(user == null){
			return;
		}
		map.clear();
		map.put("code", "true");
	}

}
