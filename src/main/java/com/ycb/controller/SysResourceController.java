package com.ycb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ycb.entity.SysResource;
import com.ycb.entity.SysResourceType;
import com.ycb.entity.SysRole;
import com.ycb.entity.SysRoleResource;
import com.ycb.entity.business.SessionInfo;
import com.ycb.entity.business.Tree;
import com.ycb.service.SysResourceService;
import com.ycb.service.SysRoleResourceService;
import com.ycb.util.ConfigUtil;
import com.ycb.util.ReturnJson;

@Controller
@RequestMapping("resource")
public class SysResourceController {
	
	@Autowired
	private SysResourceService resourceService;
	
	@Autowired
	private SysRoleResourceService roleResourceService;
	
	
	@RequestMapping("queryById")
	@ResponseBody
	public SysResource queryById(String id){
		SysResource resonurce = resourceService.queryById(id);
		return resonurce;
	}
	
	
	
	@RequestMapping("toshow")
	@ResponseBody
	public List <SysResourceType> toshow(){
		List <SysResourceType> list = roleResourceService.toshow();
		return list;
	}
	
	
	/**
	 *查询资源类型 
	 */
	@RequestMapping(value="selectResourceTypeList")
	@ResponseBody
	public List<SysResourceType> selectResourceTypeList(){
		return resourceService.selectResourceTypeList();
	}
	
	
	/**
	 *  addResource 新增
	 * @return
	 */
	@RequestMapping(value="addResource",method=RequestMethod.POST)
	@ResponseBody
	public  ReturnJson addResource(SysResource resource){
		resourceService.addResrouce(resource);
		return new ReturnJson(true,"添加成功",null);
	}
	
	/**
	 * 跳转到 addResource.jsp
	 * @return
	 */
	@RequestMapping(value="toAddResource")
	public ModelAndView toAddResource(){
		return new ModelAndView("role/addResource");
	}
	
	@RequestMapping(value="selectResourceList",method=RequestMethod.POST)
	@ResponseBody
	public List<SysResource> selectResourceList(String id){
		//查询list集合
	      List<SysResource> resourceList = 	resourceService.selectResourceList(id);
	      return resourceList;
	} 
	
	/**
	 * 跳转到 resourceList.jsp
	 * @return
	 */
	@RequestMapping("toResourceList")
	public ModelAndView toResourceList(){
		return new ModelAndView("role/resourceList");
	}
	
	/**
	 *  授予权限/修改权限
	 *  1.根据角色id删除 角色权限信息
	 *  2.添加 重新授予的 角色权限信息
	 * @param roleResource
	 * @return
	 */
	@RequestMapping(value="grantResourceOfRole",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson grantResourceOfRole(SysRoleResource roleResource){
		
		roleResourceService.updateResourceOfRole(roleResource);
		
		return new ReturnJson(true, "授予权限成功", null);
	}
	
	/**
	 * 根据角色id查询拥有的权限资源list
	 * @param role
	 * @return
	 */
	@RequestMapping(value="getResourceByRoleId",method=RequestMethod.POST)
	@ResponseBody
	public List<SysRoleResource> getResourceByRoleId(SysRole role){
		
		List<SysRoleResource> roleResourceList = roleResourceService.getResourceByRoleId(role);
		
		return roleResourceList;
	}
	
	
	/**
	 * 查询所有的权限资源tree
	 * @return
	 */
	@RequestMapping(value="getResourceTree",method=RequestMethod.POST)
	@ResponseBody
	public  ArrayList<Tree>  getResourceTree(){
		 List<SysResource> resourceList =  resourceService.getResourceTree();
		 ArrayList<Tree> treeList = new ArrayList<>();
		 //一级节点
		 Tree yiji = null;
		 //子节点list
		 ArrayList<Tree> childList = null;
		 //子级节点
		 Tree child = null;
		 //节点的自定义属性 如 url等。。。
		 HashMap<String, String> nodeAttr = null;
		 for (int i = 0; i < resourceList.size(); i++) {
			 //pid== null 说明一级节点
			 if (resourceList.get(i).getPid() == null) {
				yiji = new Tree();
				 yiji.setId(resourceList.get(i).getId());
				 yiji.setText(resourceList.get(i).getName());
				 yiji.setIconCls(resourceList.get(i).getIconCls());
				 yiji.setState("open");
				 childList = new ArrayList<>();
				 
				 //循环遍历子节点
				 for (int j = 0; j < resourceList.size(); j++) {
					 //当前循环的节点的父级id 等于  上层循环节点的id
					 if (resourceList.get(j).getPid() != null && 
							 resourceList.get(i).getId().equals(resourceList.get(j).getPid()) ) {
						 //实例化子节点
						 child = new Tree();
						 //节点属性赋值
						 child.setId(resourceList.get(j).getId());
						 child.setText(resourceList.get(j).getName());
						 child.setIconCls(resourceList.get(j).getIconCls());
						 child.setPid(resourceList.get(j).getPid());
						 child.setState("open");
						 //实例化 自定义节点属性map
						 nodeAttr = new HashMap<>();
						 nodeAttr.put("url", resourceList.get(j).getUrl());
						 child.setAttributes(nodeAttr);
						 //子节点list 添加 child节点
						 childList.add(child);
						 
						 //循环递归调用
						 selectChildList(resourceList, child);
					}
				 }
				 yiji.setChildren(childList);
				 treeList.add(yiji);
			}
		}
		return treeList;
	}
	
	
	//封装递归方法
	private void selectChildList(List<SysResource> resourceList,Tree prarentNode){
		 //子节点list
		 ArrayList<Tree> childList = new ArrayList<>();
		 //子级节点
		 Tree child = null;
		 //节点的自定义属性 如 url等。。。
		 HashMap<String, String> nodeAttr = null;
		 //循环遍历子节点
		 for (int j = 0; j < resourceList.size(); j++) {
			 //当前循环的节点的父级id 等于  上层循环节点的id
			 if (resourceList.get(j).getPid() != null && 
					 prarentNode.getId().equals(resourceList.get(j).getPid()) ) {
				 //实例化子节点
				 child = new Tree();
				 //节点属性赋值
				 child.setId(resourceList.get(j).getId());
				 child.setText(resourceList.get(j).getName());
				 child.setIconCls(resourceList.get(j).getIconCls());
				 child.setPid(resourceList.get(j).getPid());
				 child.setState("open");
				 //实例化 自定义节点属性map
				 nodeAttr = new HashMap<>();
				 nodeAttr.put("url", resourceList.get(j).getUrl());
				 child.setAttributes(nodeAttr);
				 //子节点list 添加 child节点
				 childList.add(child);
				 
				 //递归调用查找子节点 n层
				 selectChildList(resourceList, child);
			}
		 }
		prarentNode.setChildren(childList);

	}
	
	
	
	
	
	
	
	/**
	 * 左侧菜单tree  resourceType=0   菜单类型会显示在系统首页左侧菜单中
	 * @return
	 */
	@RequestMapping("selectMainMenu")
	@ResponseBody
	public List<Tree> selectMainMenu(HttpServletRequest request){
		
		SessionInfo sessionInfo =  (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		
		String userId =  sessionInfo.getUser().getId();
		
		List<Tree> treeList =  resourceService.selectMainMenu(userId);
		
		return treeList;
		
	}

}
