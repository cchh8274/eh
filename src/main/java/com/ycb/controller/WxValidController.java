package com.ycb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.util.SignUtil;

@Controller
@RequestMapping("/ycb/wx")
public class WxValidController {
	private static Logger  log=Logger.getLogger(WxValidController.class);
	private static final String token = "31ddb15";

	/**
	 * token 验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "valid", method = RequestMethod.GET)
	@ResponseBody
	public String valid(HttpServletRequest request) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}
		return "voliad fail";

	}
	/**
	 * 用户授权
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "authorization", method = RequestMethod.GET)
	public void validUser(HttpServletRequest request,HttpServletResponse response, String state,String code) {
		try {
			log.info("微信登陆成功!");
			response.sendRedirect("http://ch.yzxx-soft.com/24h/index.html?isfrist=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("重定向失败");
		}
	}
	
}
