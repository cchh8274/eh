package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysRoleResourceMapper;
import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysRoleResource;
import com.ycb.service.SysRoleResourceService;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService{
	
	@Autowired
	private SysRoleResourceMapper roleResourceDao;
	
	@Override
	public void updateResourceOfRole(SysRoleResource roleResource) {
		
//		 1.根据角色id删除 

		int x = roleResourceDao.deleteRoleResourceByRoleId(roleResource.getSysRoleId());
		
		String[] split = roleResource.getSysResourceId().split(",");
		
		List<SysRoleResource> roleResourceList = new ArrayList<>();
		
		SysRoleResource rr = null;
		
		for (int i = 0; i < split.length; i++) {
			rr = new SysRoleResource();
			rr.setSysRoleId(roleResource.getSysRoleId());
			rr.setSysResourceId(split[i]);
			
			roleResourceList.add(rr);
		}

//		 *  2.添加 重新授予的 角色权限信息
		
		roleResourceDao.insertRoleResourceList(roleResourceList);
		
	}
	
	

	@Override
	public List<SysRoleResource> getResourceByRoleId(SysRole role) {
		return roleResourceDao.getResourceByRoleId(role);
	}



	@Override
	public List<SysResourceType> toshow() {
		return roleResourceDao.toshow();
	}

}
