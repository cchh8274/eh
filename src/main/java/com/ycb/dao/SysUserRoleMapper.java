package com.ycb.dao;

import java.util.List;

import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;

public interface SysUserRoleMapper {

	int deleteUserRoleByUserId(SysUser user);

	void insertUserRoleList(List<SysUserRole> userRoleList);

}
