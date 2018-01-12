package com.ycb.dao;

import java.util.List;

import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;
import com.ycb.entity.business.UserPwd;
import com.ycb.util.PageUtil;

public interface SysUserMapper {

	//分页查询
	PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage);

	//注册用户
	void saveSysUser(SysUser user);

	//校验用户
	SysUser selectSysUserByloginName(SysUser user);

	//根据id查询用户
	SysUser selectUserById(String userId);

	//根据id修改用户密码
	void updateUserPwdById(UserPwd userPwd);

	//根据用户ID查询角色信息
	List<SysUserRole> selectUserRoleList(SysUser user);

	int selectUserCount(PageUtil<SysUser> userPage);

}
