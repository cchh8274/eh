package org.jk.user.service;

import com.ycb.entity.SysUser;


public interface UserService {

	SysUser checkSysUserLogin(SysUser user);

	void saveSysUser(SysUser user);

}
