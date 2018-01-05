package com.ycb.dao;

import java.util.List;

import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysRoleResource;

public interface  SysRoleResourceMapper{

	//根据角色id查询拥有的权限资源list
	List<SysRoleResource> getResourceByRoleId(SysRole role);

	//根据角色id删除 角色权限信息
	int deleteRoleResourceByRoleId(String sysRoleId);

	//批量添加 角色权限信息
	void insertRoleResourceList(List<SysRoleResource> roleResourceList);

	List<SysResourceType> toshow();

}
