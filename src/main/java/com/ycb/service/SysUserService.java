package com.ycb.service;

import java.util.List;

import com.ycb.entity.SysRole;
import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;
import com.ycb.entity.business.UserPwd;
import com.ycb.util.PageUtil;

public interface SysUserService {

	//分页查询
	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);

	//注册用户--新增
	void saveSysUser(SysUser user);

	//校验用户
	SysUser checkSysUser(SysUser user);

	//根据id查询用户
	SysUser selectUserById(String userId);

	
	//根据id修改密码
	void updateUserPwdById(UserPwd userPwd);

	
	//获取角色tree
	List<SysRole> getRoleTree();

	//根据用户ID查询角色信息
	List<SysUserRole> selectUserRoleList(SysUser user);

	////用户授予角色，分配角色
	//根据用户ID删除之前的角色信息
	//将用户重新授予角色信息 添加 到  用户角色关联关系表
	void updateRoleOfUser(SysUser user);
	
}
