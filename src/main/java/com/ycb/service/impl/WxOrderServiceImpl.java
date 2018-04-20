package com.ycb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kanmars.ecm.dao.TblAreaInfoMapper;
import cn.kanmars.ecm.dao.TblMachineInfoMapper;
import cn.kanmars.ecm.dao.TblOrderDealMapper;
import cn.kanmars.ecm.dao.TblOrderInfoMapper;
import cn.kanmars.ecm.dao.TblUniversityInfoMapper;
import cn.kanmars.ecm.dao.TblWxUserInfoMapper;

import com.ycb.dao.WxOrderMapper;
import com.ycb.entity.WxOrder;
import com.ycb.entity.WxUser;
import com.ycb.service.WxOrderService;
import com.ycb.util.AmountUtils;
import com.ycb.util.Constants;
import com.ycb.util.DateUtils;

@Service
public class WxOrderServiceImpl implements WxOrderService {
	@Autowired
	private WxOrderMapper wxOrderMapper;
	
	@Autowired
	private TblOrderDealMapper tblOrderDealMapper;
	@Autowired
	private TblOrderInfoMapper tblOrderInfoMapper;
	@Autowired
	private TblAreaInfoMapper tblAreaInfoMapper;
	@Autowired
	private TblUniversityInfoMapper tblUniversityInfoMapper;
	@Autowired
	private TblWxUserInfoMapper tblWxUserInfoMapper;
	@Autowired
	private TblMachineInfoMapper tblMachineInfoMapper;
	
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

	@Override
	public void queryWxOrderAll(Map<String, Object> map) {
		int total=wxOrderMapper.selectCountOrder(map);
		if(total<0){
			return ;
		}
		map.put("total", total);
//		map.put("rows", wxOrderMapper.queryWxOrderAll(map));
	}
	
	/**
	 * 同步微信用户
	 */
	@Override
	public List<String> queryUsergroupby() {
		List<String> list=wxOrderMapper.queryUsergroupby();
		return list;
	}
	/**
	 * 查询支付成功的订单
	 */
	@Override
	public List<HashMap> queryDealList(String yesterday) {
		HashMap  sql=new HashMap();
		sql.put("createTime", yesterday);
		sql.put("payStatus", Constants.PAY_STATUS_SUCCESS);
		return tblOrderDealMapper.queryListMap(sql);
	}

	@Override
	public void queryBatisinfo(HashMap hashMap) {
		HashMap  map=new HashMap();
		map.put("openid", hashMap.get("openid"));
		hashMap.put("userName", tblWxUserInfoMapper.queryOneMap(map).get("userName"));
		map.clear();
		map.put("id", hashMap.get("manicheId"));
		hashMap.put("manicheName", tblMachineInfoMapper.queryOneMap(map).get("manicheName"));
		map.clear();
		map.put("id", hashMap.get("areaId"));
		hashMap.put("areaName", tblAreaInfoMapper.queryOneMap(map).get("areaName"));
		map.clear();
		map.put("id", hashMap.get("universityId"));
		hashMap.put("unversityName", tblUniversityInfoMapper.queryOneMap(map).get("unversityName"));
	}

	@Override
	public void insertBatch(List<HashMap> list) throws Exception{
		tblOrderInfoMapper.insertBatch(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
