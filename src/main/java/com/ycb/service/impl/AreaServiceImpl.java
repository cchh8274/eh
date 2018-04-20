package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.kanmars.ecm.dao.TblAreaInfoMapper;
import cn.kanmars.ecm.dao.TblUniversityInfoMapper;
import cn.kanmars.ecm.entity.TblAreaInfo;
import cn.kanmars.ecm.entity.TblUniversityInfo;

import com.alibaba.fastjson.JSON;
import com.ycb.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private TblAreaInfoMapper tblAreaInfoMapper;
	@Autowired
	private TblUniversityInfoMapper tblUniversityInfoMapper;

	@Override
	public String selectArea() throws Exception {
		List<TblAreaInfo> list = tblAreaInfoMapper.selectList(null);
		if(CollectionUtils.isEmpty(list)){
			return "";
		}
		List<HashMap<String,String>> infos=new ArrayList<HashMap<String,String>>();
		for (TblAreaInfo tblAreaInfo : list) {
			HashMap<String,String> info=new HashMap<String,String>();
			info.put("name", tblAreaInfo.getAreaName());
			info.put("code", tblAreaInfo.getId().toString());
			TblUniversityInfo  unversity=new TblUniversityInfo();
			unversity.setAreaId(tblAreaInfo.getId().toString());
			List<TblUniversityInfo>  unversitys=tblUniversityInfoMapper.selectList(unversity);
			info.put("unversity", JSON.toJSONString(unversitys));
			infos.add(info);
		}
		return JSON.toJSONString(infos);
	}

}
