package com.ycb.service;

import java.util.List;

import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysRoleResource;

public interface SysRoleResourceService {

	//根据角色id查询拥有的权限资源list
	List<SysRoleResource> getResourceByRoleId(SysRole role);

//	*  授予权限/修改权限
//	 *  1.根据角色id删除 角色权限信息
//	 *  2.添加 重新授予的 角色权限信息
	void updateResourceOfRole(SysRoleResource roleResource);

	List<SysResourceType> toshow();

}
