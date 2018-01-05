package com.ycb.dao;

import com.ycb.entity.SysUser;

public interface SyStemMapper {
	
	SysUser selectSysUserByloginName(String loginname);
	
	void  insertSysUser(SysUser user);
}
