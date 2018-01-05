package com.ycb.service;

import java.util.List;

import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;

public interface SysUserRoleService {

	int deleteUserRoleByUserId(SysUser user);

	void insertUserRoleList(List<SysUserRole> userRoleList);

}
