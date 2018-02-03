package com.ycb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.WxOrderMapper;
import com.ycb.entity.WxOrder;
import com.ycb.service.WxOrderService;
import com.ycb.util.AmountUtils;
import com.ycb.util.DateUtils;

@Service
public class WxOrderServiceImpl implements WxOrderService {
	@Autowired
	private WxOrderMapper wxOrderMapper;
	
	@Override
	public String queryCountManchine(String openID) {
		return wxOrderMapper.queryCountManchine(openID);
	}
	
	@Override
	public List<Map<String, Object>> queryMachinebyOpenid(String openID) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		List<WxOrder>  list=wxOrderMapper.queryMachinebyOpenid(openID);
		List<Map<String,String>>  data=new ArrayList<Map<String,String>>();
			for (WxOrder wxOrder : list) {
				Map<String,String> detailsMap=new HashMap<String, String>();
				detailsMap.put("area", wxOrder.getArea());
				detailsMap.put("unit", wxOrder.getUnit());
				detailsMap.put("machine", wxOrder.getMachine());
				detailsMap.put("num", wxOrder.getNum());
				detailsMap.put("money", AmountUtils.changeF2Y(Long.valueOf(wxOrder.getTotalFee())/Long.valueOf(wxOrder.getNum())));
				detailsMap.put("paytime", DateUtils.formatDate(wxOrder.getPaytime(), "yyyy年MM月dd日"));
				data.add(detailsMap);
			}
		listToMap(map,data);
		List<Map<String,Object>>  maps=new ArrayList<Map<String,Object>>();
		for (String key : map.keySet()) {
			Map<String,Object>  dataDetail=new HashMap<String, Object>();
			dataDetail.put("area", key);
			dataDetail.put("data", map.get(key));
			maps.add(dataDetail);
		}
		
		return maps;
	}
	
	private static  void  listToMap(Map<String,Object> map,List<Map<String,String>> list){
		for (Map<String,String> wxOrder : list) {
			if(map.get(wxOrder.get("area"))==null){
				List<Map<String,String>>  units=new ArrayList<Map<String,String>>();
				units.add(wxOrder);
				map.put(wxOrder.get("area"), units);
			}else{
				List<Map<String,String>>  units=(List<Map<String,String>>) map.get(wxOrder.get("area"));
				units.add(wxOrder);
			}
		}
	}
	
}	
