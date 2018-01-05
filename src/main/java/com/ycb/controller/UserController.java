package com.ycb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.entity.SysUser;
import com.ycb.entity.business.SessionInfo;
import com.ycb.util.ConfigUtil;
import com.ycb.util.MD5Util;
import com.ycb.util.ReturnJson;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 校验用户登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkSysUserLogin", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUserLogin(SysUser user,
			HttpServletRequest request, Integer flag) {
		ReturnJson rj = new ReturnJson();
		try {
			// 校验数据
			userFeng(user, rj, request);
		} catch (Exception e) {
			rj.setSuccess(false);
			rj.setMsg("系统错误, 请联系管理员!!");
		}

		return rj;
	}

	public void userFeng(SysUser user, ReturnJson rj, HttpServletRequest request) throws Exception{
		if (user == null) {
			rj.setSuccess(false);
			rj.setMsg("请输入用户名或者密码");
			return;
		}
//		if (StringUtils.isBlank(user.getImgcode())) {
//			rj.setSuccess(false);
//			rj.setMsg("请输入验证码");
//			return;
//		}
//		// 获取session中的验证码
//		String code = (String) request.getSession().getAttribute("imageCode");
//		if (!StringUtils.equals(code, user.getImgcode())) {
//			rj.setSuccess(false);
//			rj.setMsg("验证码输入错误!");
//			return;
//		}
		SysUser u = userService.checkSysUserLogin(user);
		if (null != u) {// 用户名正确
			if (u.getPwd().equals(MD5Util.md5(user.getPwd()))) {// 密码正确
				rj.setSuccess(true);
				rj.setMsg("登录成功");
				// 登录成功之后将用户信息存放到session中
				SessionInfo sessionInfo = new SessionInfo();
				// 用户密码置 空
				u.setPwd(null);
				sessionInfo.setUser(u);
				request.getSession().setAttribute(
						ConfigUtil.getSessionInfoName(), sessionInfo);
			} else {
				rj.setSuccess(false);
				rj.setMsg("密码错误");
			}
		} else {// 用户名错误!
			rj.setSuccess(false);
			rj.setMsg("用户名错误");
		}
	}

	/**
	 * 校验用户---账户名称是否已存在
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkSysUser", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson checkSysUser(SysUser user) {
		ReturnJson rj = new ReturnJson();

		SysUser u = userService.checkSysUserLogin(user);

		if (null != u) {
			rj.setSuccess(false);// 已经被注册
		} else {
			rj.setSuccess(true);
		}
		return rj;
	}

}
