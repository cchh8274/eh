package com.ycb.service;

import java.util.List;

import com.ycb.entity.SysRole;
import com.ycb.util.PageUtil;

public interface SysRoleService {

	List<SysRole> getRoleTree();

	PageUtil<SysRole> selectRoleList(PageUtil<SysRole> rolePage);

}
