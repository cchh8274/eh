package com.ycb.service;

import java.util.List;
import java.util.Map;

import com.ycb.entity.SysResource;
import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysUser;
import com.ycb.entity.business.Tree;
import com.ycb.util.PageUtil;

public interface SysResourceService {

	//查询左侧菜单树
	List<Tree> selectMainMenu(String userId);

	//查询所有资源树
	List<SysResource> getResourceTree();

//	根据用户id查询拥有的resource资源权限
	List<SysResource> selectResourceByUserId(String userId);

	List<SysResource> selectResourceList(String id);

	
	//查询 资源类型 list
	List<SysResourceType> selectResourceTypeList();

	void addResrouce(SysResource resource);

	SysResource queryById(String id);

	PageUtil<SysResource> selectMenuList(PageUtil<SysResource> userPage);

	void delRes(Map<String, String> map);

	void inserRes(SysResource sysResource);

}
