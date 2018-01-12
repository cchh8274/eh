package com.ycb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.SysRole;
import com.ycb.entity.SysUser;
import com.ycb.entity.SysUserRole;
import com.ycb.entity.business.SessionInfo;
import com.ycb.entity.business.Tree;
import com.ycb.entity.business.UserPwd;
import com.ycb.service.SysUserService;
import com.ycb.util.ConfigUtil;
import com.ycb.util.MD5Util;
import com.ycb.util.PageUtil;
import com.ycb.util.ReturnJson;



@Controller
@RequestMapping("user")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;
	
	//用户授予角色，分配角色
	@RequestMapping(value="grantRoleOfUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson grantRoleOfUser(SysUser user){
		userService.updateRoleOfUser(user);
		return new ReturnJson(true, "修改角色成功",null);
	}
	
	/**
	 * 根据用户id查询拥有的角色信息
	 * @param user
	 */
	@RequestMapping(value="getUserRoleInfo",method=RequestMethod.POST)
	@ResponseBody
	public List<SysUserRole>  getUserRoleInfo(SysUser user){
		
		List<SysUserRole> userRoleList =  userService.selectUserRoleList(user);
		
		
		return userRoleList;
	}
	
	
	/**
	 * 查询角色tree
	 * @return
	 */
	@RequestMapping(value="getRoleTree",method=RequestMethod.POST)
	@ResponseBody
	public List<Tree> getRoleTree(){
		List<SysRole> roleList =  userService.getRoleTree();
		
		List<Tree> treeList = new ArrayList<>();
		
		Tree t = null;
		
		for (SysRole role : roleList) {
			t = new Tree();
			t.setId(role.getId());
			t.setText(role.getName());
			treeList.add(t);
		}
		
		
		return treeList;
	}
	
	
	/**
	 * 注销退出系统
	 * @param request
	 */
	@RequestMapping("logoutSysUser")
	@ResponseBody
	public void logoutSysUser(HttpServletRequest request){
		request.getSession().removeAttribute(ConfigUtil.getSessionInfoName());
	}
	
	
	@RequestMapping(value="updateSysUserPwd",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson updateSysUserPwd(UserPwd userPwd,HttpServletRequest request){
		ReturnJson rj = new ReturnJson();
		
		//用户ID----session获取 ，隐藏域
		SessionInfo sessionInfo =  (SessionInfo) request.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String userId = sessionInfo.getUser().getId();
		
		//1.根据用户ID查询旧密码是否与数据库一致
		
		//如果session存放的用户对象中的密码是属性值为null
		
		SysUser u  = userService.selectUserById(userId);
		
		//旧密码一致
		if (null != u && u.getPwd().equals(MD5Util.md5(userPwd.getOldPwd()))) {
			userPwd.setUserId(userId);
			userService.updateUserPwdById(userPwd);
			rj.setSuccess(true);
			rj.setMsg("修改密码成功");
		} else {
			rj.setSuccess(false);
			rj.setMsg("旧密码错误，修改密码失败");
		}
		
		return rj;
	}
	
	
	/**
	 * 校验用户登录
	 * @return
	 */
	@RequestMapping(value="checkSysUserLogin",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUserLogin(SysUser user,HttpServletRequest request,Integer flag){
		ReturnJson rj = new ReturnJson();
		
		//获取session中的验证码
				String code = (String) request.getSession().getAttribute("imageCode");
				
				
				if (null !=flag && 1 !=flag) {
					//校验验证码是否正确
					if (null != user && !"".equals(user.getImgcode().trim()) && !"".equals(code)) {
						//验证码正确---不区分大小写
						if (user.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
							userFeng(user,rj,request);
						}
					}
				} else {
					userFeng(user,rj,request);
				}
				
			
	    
		return rj;
	}
	
	
	public void userFeng(SysUser user,ReturnJson rj,HttpServletRequest request){
		//u 查询数据库的信息   
		//user 用户登录时表单
		SysUser u = userService.checkSysUser(user);
		if (null != u) {//用户名正确
			if (u.getPwd().equals(MD5Util.md5(user.getPwd()))) {//密码正确
				rj.setSuccess(true);
				rj.setMsg("登录成功");
				//登录成功之后将用户信息存放到session中
				SessionInfo sessionInfo = new SessionInfo();
				//用户密码置 空
				u.setPwd(null);
				sessionInfo.setUser(u);
				request.getSession().setAttribute(ConfigUtil.getSessionInfoName(),sessionInfo);
			} else {
				rj.setSuccess(false);
				rj.setMsg("密码错误");
			}
		} else {//用户名粗我
				rj.setSuccess(false);
				rj.setMsg("用户名错误");
		}
	}
	
	
	
	
	/**
	 * 校验用户---账户名称是否已存在
	 * @return
	 */
	@RequestMapping(value="checkSysUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUser(SysUser user){
		ReturnJson rj = new ReturnJson();
		
	    SysUser u = userService.checkSysUser(user);
		
	    if (null != u) {
			rj.setSuccess(false);//已经被注册
		}else{
			rj.setSuccess(true);
		}
		return rj;
	}
	
	
	
	/**
	 * 用户注册  ---添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="registerSysUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJson registerSysUser(SysUser user, HttpServletRequest request){
		ReturnJson  rj = new ReturnJson();
		//获取session中的验证码
		String code = (String) request.getSession().getAttribute("imageCode");
		//校验验证码是否正确
		if (null != user && !"".equals(user.getImgcode().trim()) && !"".equals(code)) {
			//验证码正确---不区分大小写
			if (user.getImgcode().trim().toUpperCase().equals(code.toUpperCase())) {
				userService.saveSysUser(user);
				// 主键id ,布尔类型 ,影响数据库的条数
				rj.setSuccess(true);
				rj.setMsg("注册成功");
			}else{
				rj.setSuccess(false);
				rj.setMsg("验证码错误");
			}
		}
		return rj;
		
	}
	
	
	
	
	/**
	 * @param page  当前页
	 * @param rows  每页条数
	 * @return 分页user信息
	 */
	@RequestMapping("selectUserList")
	@ResponseBody
	public Map<String,Object> selectUserList(Integer page,Integer rows,PageUtil<SysUser> userPage){
		userPage.setCpage(page);
		userPage.setPageSize(rows);
		userPage = userService.selectUserList(userPage);
		Map<String,Object> map = new HashMap<>();
		map.put("total", userPage.getTotalCount());
		map.put("rows", userPage.getList());
		return map;
	}
	
	@RequestMapping("toUserList")
	public String toUserList(){
		return "user/userList";
	}
	
	
	

}
