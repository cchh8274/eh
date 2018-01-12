package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysUserMapper;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;
import com.ycb.entity.business.UserPwd;
import com.ycb.service.SysRoleService;
import com.ycb.service.SysUserRoleService;
import com.ycb.service.SysUserService;
import com.ycb.util.MD5Util;
import com.ycb.util.PageUtil;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper userDao;

	// 注入 roleService 角色Service层
	@Autowired
	private SysRoleService roleService;

	@Autowired
	private SysUserRoleService userRoleService;

	@Override
	public void updateRoleOfUser(SysUser user) {

		// 根据用户ID删除之前的角色信息--- 删除 用户角色关联关系表

		int x = userRoleService.deleteUserRoleByUserId(user);

		// 将用户重新授予角色信息 添加 到 用户角色关联关系表

		String[] split = user.getRoleIds().split(",");

		List<SysUserRole> userRoleList = new ArrayList<>();
		SysUserRole ur = null;
		for (int i = 0; i < split.length; i++) {
			ur = new SysUserRole();
			ur.setSysUserId(user.getId());
			ur.setSysRoleId(split[i]);
			userRoleList.add(ur);
		}

		userRoleService.insertUserRoleList(userRoleList);

	}

	@Override
	public List<SysUserRole> selectUserRoleList(SysUser user) {
		return userDao.selectUserRoleList(user);
	}

	@Override
	public List<SysRole> getRoleTree() {

		List<SysRole> roleTreeList = roleService.getRoleTree();

		return roleTreeList;
	}

	@Override
	public void updateUserPwdById(UserPwd userPwd) {

		userDao.updateUserPwdById(userPwd);
	}

	@Override
	public SysUser selectUserById(String userId) {
		return userDao.selectUserById(userId);
	}

	@Override
	public SysUser checkSysUser(SysUser user) {
		return userDao.selectSysUserByloginName(user);
	}

	@Override
	public void saveSysUser(SysUser user) {
		// 随机数id
		user.setId(UUID.randomUUID().toString());
		// 密码加密
		user.setPwd(MD5Util.md5(user.getPwd()));

		// 创建时间
		user.setCreatedatetime(new Date());
		// 修改时间
		user.setUpdatedatetime(new Date());

		userDao.saveSysUser(user);
	}

	public PageUtil<SysUser> selectUserList(PageUtil<SysUser> userPage) {
		int totalCount = (int) userDao.selectUserCount(userPage);
		List<SysUser> userList =  userDao.selectUserList(userPage);
		userPage.setTotalCount(totalCount);
		userPage.setList(userList);
		return userPage;
	}

}
