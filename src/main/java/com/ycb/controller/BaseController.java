package com.ycb.controller;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 每次都返回前端JSON字符串
 * @author chenghui
 *
 */
public class BaseController  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  String  SUCCESS="success";
	protected  String  ERROR="error";
	
	
	public String toJSONString(Object obj) {
		String json=JSON.toJSONString(obj);
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("code",this.SUCCESS);
		jsonObj.put("info",json);
		return jsonObj.toJSONString();
	}
	
	public String toJSONString(String str) {
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("code",this.SUCCESS);
		jsonObj.put("info",str);
		return jsonObj.toJSONString();
	}
	public String toJSONString(String code,String str) {
		JSONObject  jsonObj=new JSONObject();
		jsonObj.put("code",this.ERROR);
		jsonObj.put("info",str);
		return jsonObj.toJSONString();
	}

}
