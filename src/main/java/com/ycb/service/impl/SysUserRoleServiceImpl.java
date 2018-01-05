package com.ycb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysUserRoleMapper;
import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;
import com.ycb.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleMapper userRoleDao;

	@Override
	public int deleteUserRoleByUserId(SysUser user) {
		
		int x = userRoleDao.deleteUserRoleByUserId(user);
		
		return x ;
	}

	@Override
	public void insertUserRoleList(List<SysUserRole> userRoleList) {
		userRoleDao.insertUserRoleList(userRoleList);
	}
	
	
	
}
