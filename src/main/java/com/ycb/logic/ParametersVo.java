package com.ycb.logic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class ParametersVo<K, V> extends HashMap<K, V> implements Serializable {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 15977899551804329L;
	/** 001 002 **/
	private String resCode;
	private String resDesc;
	private Map<String,Object> obj=null;
	
	
	/**
	 * 返回前端JSON字符串
	 * @param vo
	 * @return
	 */
	public  String toResultString(){
		this.clear(); //清除所有数据
		return JSON.toJSONString(this);
	}
	

	/**
	 * 返回字符串
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String resultValue = "";
		if (StringUtils.isBlank(key)) {
			resultValue = "";
		} else {
			Object value = this.get(key);
			if (value != null) {
				if (value.getClass().getSimpleName().equals("BigDecimal")) {
					resultValue = ((BigDecimal) value).toPlainString();
				} else {
					resultValue = String.valueOf(value);
				}
			}
		}
		return resultValue;
	}

	private static final String code = "001";
	private static final String desc = "成功";

	public ParametersVo() {
		super();
		this.resCode = code;
		this.resDesc = desc;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}


	public Map<String, Object> getObj() {
		return obj;
	}


	public void setObj(Map<String, Object> obj) {
		this.obj = obj;
	}
}
