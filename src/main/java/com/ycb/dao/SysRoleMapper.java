package com.ycb.dao;

import java.util.List;

import com.ycb.entity.SysRole;
import com.ycb.util.PageUtil;

public interface SysRoleMapper {

	List<SysRole> getRoleTree();

	PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);

	int selectRoleListCount(PageUtil<SysRole> rolePage);

	List<SysRole> selectRoleListPage(PageUtil<SysRole> rolePage);

}
