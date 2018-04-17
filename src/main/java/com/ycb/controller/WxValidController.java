package com.ycb.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPayUtil;
/**
 * 验证微信服务器端
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/ycb/wx")
public class WxValidController {
	private static Logger log = Logger.getLogger(WxValidController.class);
	private static final String token = "31ddb15";
	
	
	private static final String session="Session-Customer-service";
	
	/**
	 * token 验证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "valid", method = RequestMethod.POST)
	@ResponseBody
	public String valid(HttpServletRequest request) {
		try {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			String openid = request.getParameter("openid");
			System.out.println("接收到的openid为=》"+openid);
			System.out.println("signature=>" + signature + "echostr =>"
					+ echostr);
			InputStream inStream = request.getInputStream();
			int _buffer_size = 1024;
			Map<String,String> hashMap=new HashMap<String, String>();
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[_buffer_size];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();
				// 将流转换成字符串
				String result = new String(outStream.toByteArray(), "UTF-8");
				System.out.println("fasongxiaos获取的报文结构为=》" + result);
				 Map<String,String> resultMap = WXPayUtil.xmlToMap(result);
				 responseText(resultMap.get("FromUserName"),resultMap.get("ToUserName"),hashMap);
				 String xml= WXPayUtil.mapToXml(hashMap);
				 String xmlnew=new String(xml.getBytes(),"utf-8");
				 return xmlnew;
			}

//			hashMap.put("echostr", echostr);
			createSeesion(openid,hashMap);
			return WXPayUtil.mapToXml(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "voliad fail";
		}

	}
	
	/**
	 * 客服回复
	 * @param openid
	 * @param map
	 */
	private void  createSeesion(String openid,Map<String,String> map){
		map.put("ToUserName", openid);
		map.put("FromUserName", "cchh_8274");
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddhhmmss");
		map.put("CreateTime", sim.format(new Date()));
		map.put("MsgType", "transfer_customer_service");
	}
	
	/**
	 * 启动客服功能
	 */
	private void  createInitSeesion(String openid,Map<String,String> map){
		map.put("ToUserName", openid);
		map.put("FromUserName", "oYYPb0sIjQ8g4Gs6pNuMI1tnTcyI");
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddhhmmss");
		map.put("CreateTime", sim.format(new Date()));
		map.put("MsgType", "text");
		map.put("Content","欢迎欢迎!");
	}
		
	/**
	 * 回复文本消息
	 */
	private void  responseText(String openid,String username,Map<String,String> map){
		map.put("ToUserName", openid);
		map.put("FromUserName", username);
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMddhhmmss");
		map.put("CreateTime", sim.format(new Date()));
		map.put("MsgType", "text");
		map.put("Content","欢迎欢迎!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 用户授权
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "authorization", method = RequestMethod.GET)
	public void validUser(HttpServletRequest request,
			HttpServletResponse response, String state, String code) {
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
