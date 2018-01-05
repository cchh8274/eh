package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysRoleMapper;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysUser;
import com.ycb.service.SysRoleService;
import com.ycb.util.PageUtil;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleDao;
	
	@Override
	public PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage) {
		return sysRoleDao.selectRoleList(rolePage);
	}
	
	@Override
	public List<SysRole> getRoleTree() {
		
		List<SysRole> roleList = sysRoleDao.getRoleTree();
		
		Collections.sort(roleList);
		
		List<SysUser> userList = new ArrayList<>();
		Collections.sort(userList, new Comparator<SysUser>() {
			@Override
			public int compare(SysUser o1, SysUser o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		return roleList;
	}
	
}
