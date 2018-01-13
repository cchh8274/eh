package com.ycb.dao;

import java.util.List;
import java.util.Map;

import com.ycb.entity.SysResource;
import com.ycb.util.PageUtil;

public interface SysResourceMapper {

		//查询左侧菜单树
		List<SysResource> selectMainMenu(Map<String,String> map);

		//查询所有资源树
		List<SysResource> selectResourceTree();

//		根据用户id查询拥有的resource资源权限
		List<SysResource> selectResourceByUserId(String userId);

		SysResource queryById(String id);

		int selectMenuCount(PageUtil<SysResource> userPage);

		List<SysResource> selectUserList(PageUtil<SysResource> userPage);

		List<SysResource> selectResourceList();


		SysResource selectPid(String string);

		void delres(String string);

		void inserRes(SysResource sysResource);


}
