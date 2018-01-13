package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysResourceMapper;
import com.ycb.entity.SysResource;
import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysUser;
import com.ycb.entity.business.Tree;
import com.ycb.service.SysResourceService;
import com.ycb.util.PageUtil;

@Service
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysResourceMapper resourceDao;

	@Override
	public void addResrouce(SysResource resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SysResourceType> selectResourceTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysResource> selectResourceList(String id) {
		return resourceDao.selectResourceList();
	}

	@Override
	public List<SysResource> getResourceTree() {
		return resourceDao.selectResourceTree();
	}

	@Override
	public List<SysResource> selectResourceByUserId(String userId) {
		List<SysResource> resourceList = resourceDao
				.selectResourceByUserId(userId);
		// 去重
		resourceList = new ArrayList<>(new HashSet<>(resourceList));
		return resourceList;
	}

	@Override
	public List<Tree> selectMainMenu(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("resourceTypeId", "0");
		List<SysResource> resourceList = resourceDao.selectMainMenu(map);

//		List<SysResource> list = removeDuplicate(resourceList);

		List<Tree> treeList = new ArrayList<Tree>();
		Tree fjtree = null;
		Tree zjtree = null;
		List<Tree> childTree = null;
		HashMap<String, String> url = null;
		for (SysResource r : resourceList) {
			fjtree = new Tree();
			if (null == r.getPid()) {
				childTree = new ArrayList<>();
				fjtree.setId(r.getId());
				fjtree.setPid(r.getPid());
				fjtree.setText(r.getName());
				fjtree.setState("closed");
				fjtree.setIconCls(r.getIconCls());
				for (SysResource zir : resourceList) {
					if (null != zir.getPid() && zir.getPid().equals(r.getId())) {
						zjtree = new Tree();
						zjtree.setId(zir.getIconCls());
						zjtree.setPid(zir.getPid());
						zjtree.setText(zir.getName());
						zjtree.setIconCls(zir.getIconCls());
						url = new HashMap<>();
						url.put("url", zir.getUrl());
						zjtree.setAttributes(url);
						//
						childTree.add(zjtree);
					}
				}
				fjtree.setChildren(childTree);
			} else {
				continue;
			}
			treeList.add(fjtree);
		}

		return treeList;
	}

	public static List<SysResource> removeDuplicate(List<SysResource> list) {

		// 实例化 hashSet
		Set<SysResource> set = new HashSet<SysResource>();

		// 实例化 ArrayList
		List<SysResource> newList = new ArrayList<SysResource>();

		/*
		 * Iterator<SysResource> it = list.iterator(); while( it.hasNext()){
		 * SysResource element = it.next(); }
		 */

		// add() 返回boolean类型 true代表向集合添加元素成功 false代表向集合添加元素失败

		for (Iterator<SysResource> iter = list.iterator(); iter.hasNext();) {

			SysResource element = iter.next();

			if (set.add(element)) { // 返回true添加到newList //返回false不会添加newList

				newList.add(element);
			}
		}
		return newList;
	}

	@Override
	public SysResource queryById(String id) {
		// TODO Auto-generated method stub
		return resourceDao.queryById(id);
	}

	@Override
	public PageUtil<SysResource> selectMenuList(PageUtil<SysResource> userPage) {
			int totalCount = (int) resourceDao.selectMenuCount(userPage);
			List<SysResource> userList =  resourceDao.selectUserList(userPage);
			userPage.setTotalCount(totalCount);
			userPage.setList(userList);
			return userPage;
		}

	@Override
	public void delRes(Map<String, String> map) {
		SysResource re=resourceDao.selectPid(map.get("id"));
		if(StringUtils.isEmpty(re.getPid())){
			map.put("code", "false");
			map.put("message", "请先删除子节点");
			return;
		}
		resourceDao.delres(map.get("id"));
	}

	@Override
	public void inserRes(SysResource sysResource) {
		resourceDao.inserRes(sysResource);
	}
	
	
}
