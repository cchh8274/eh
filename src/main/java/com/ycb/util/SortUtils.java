package com.ycb.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class SortUtils {
	
	private static final Logger logger = Logger.getLogger(SortUtils.class);

	public static String sortString(Map<String,String> map) {
		//字典序排序  
		Set<String> keyset= map.keySet();   
		  
		List<String> list=new ArrayList<String>(keyset);  
		  
		Collections.sort(list);  
		//这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的  
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){  
			sb.append(list.get(i)+"=").append(map.get(list.get(i))).append("&");
		}  
		logger.info("排序后的字符串为=》"+sb.toString()); 
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		
	}
}
