package com.ycb.service;

import java.util.Map;

import com.ycb.entity.SysUser;


public interface UserService {

	SysUser checkSysUserLogin(SysUser user);

	void saveSysUser(SysUser user);
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 */
	void login(Map<String, String> map);

}
