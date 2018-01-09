package com.ycb.dao;

import java.util.List;
import java.util.Map;

import com.ycb.entity.SysResource;

public interface SysResourceMapper {

		//查询左侧菜单树
		List<SysResource> selectMainMenu(Map<String,String> map);

		//查询所有资源树
		List<SysResource> getResourceTree();

//		根据用户id查询拥有的resource资源权限
		List<SysResource> selectResourceByUserId(String userId);

		List<SysResource> selectResourceList(String id);

		SysResource queryById(String id);


}
