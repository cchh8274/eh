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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.SysResourceMapper;
import com.ycb.entity.SysResource;
import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysUser;
import com.ycb.entity.business.Tree;
import com.ycb.service.SysResourceService;

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
	public List<SysResource> selectResourceList(String  id) {
		
		List<SysResource> selectResourceList = resourceDao.selectResourceList(id);
		
		
		
		return selectResourceList;
	}
	
	@Override
	public List<SysResource> getResourceTree() {
		return resourceDao.getResourceTree();
	}
	
	@Override
	public List<SysResource> selectResourceByUserId(String userId) {
		List<SysResource> resourceList =  resourceDao.selectResourceByUserId(userId);
		//去重
		resourceList = new ArrayList<>(new HashSet<>(resourceList));
		return resourceList;
	}
	
	@Override
	public List<Tree> selectMainMenu(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("resourceTypeId", "0");
		List<SysResource> resourceList =  resourceDao.selectMainMenu(map);
		
		
		
		
		//利用set的无序不重复的特性用来去重---重复数据---java基本数据类型可以，引用数据类型不可以。原因：JVM虚拟机的堆栈！！！
		
		//java基本数据类型---默认已经重写 hashCode() 与 equals()方法
		
		//java引用数据类型-- 利用set的无序不重复的特性用来去重--必须重写hashCode() 与 equals()方法 
		//--重写之后两个对象是否相同通过比较属性值来决定
		
		
		// 去重方式一
		resourceList = new ArrayList<SysResource>(new HashSet<SysResource>(resourceList));
		
		//去重方式二
		List<SysResource>   list = removeDuplicate(resourceList);
		
		//排序
		Collections.sort(resourceList, new Comparator<SysResource>() {
			@Override
			public int compare(SysResource o1, SysResource o2) {
				if (o1.getSeq() == null) {
					o1.setSeq(1000);
				}
				if (o2.getSeq() == null) {
					o2.setSeq(1000);
				}
				return o1.getSeq().compareTo(o2.getSeq());
			}
		});
		
		List<Tree> treeList = new ArrayList<Tree>();
		Tree fjtree = null;
		Tree zjtree = null;
		List<Tree> childTree = null;
		HashMap<String, String> url = null;
		for (SysResource r : resourceList) {
				fjtree = new Tree();
			if (null == r.getPid() ) {
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
						url = new  HashMap<>();
						url.put("url", zir.getUrl());
						zjtree.setAttributes(url);
						//
						childTree.add(zjtree);
					}
				}
				fjtree.setChildren(childTree);
			}else{
				continue;
			}
			treeList.add(fjtree);
		}
		
		
		return treeList;
	}
	
	
	 public static List<SysResource> removeDuplicate(List<SysResource> list){
		 
		 	//实例化 hashSet
	        Set<SysResource> set = new HashSet<SysResource>();
	        
	        //实例化 ArrayList
	        List<SysResource> newList = new ArrayList<SysResource>();
	        
	      /*  Iterator<SysResource> it =  list.iterator();
	        while( it.hasNext()){
	        	SysResource element = it.next();
	        }*/
	        
	        //  add() 返回boolean类型  true代表向集合添加元素成功   false代表向集合添加元素失败
	        
	        
	        for(Iterator<SysResource> iter = list.iterator(); iter.hasNext();){
	        	
	        	SysResource element = iter.next();
	        	
	            if(set.add(element)){ //返回true添加到newList //返回false不会添加newList
	            	
	                newList.add(element);
	            }
	        }
	        return newList;
	    }
	
	 
	 	public static void main(String[] args) {
			
	 		Set<Object>  set = new HashSet<>();
			
	 		boolean b = set.add(5);
	 		
	 		boolean b2 =  set.add(8);
	 		
	 		boolean b3 =  set.add(5);
	 		
	 		SysUser u1 = new SysUser();
	 		u1.setName("张三");
	 		
	 		SysUser u2 = new SysUser();
	 		u2.setName("张三");
	 		
	 		boolean b4 = set.add(u1);
	 		
	 		boolean b5 = set.add(u2);
			
	 		System.out.println(b +"---"+b2+"---"+b3+"---"+set.size());
	 		
		}

		@Override
		public SysResource queryById(String id) {
			// TODO Auto-generated method stub
			return resourceDao.queryById(id);
		}
		
}

