package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ycb.entity.TblSysMenu;
import com.ycb.model.MenuInfo;
import com.ycb.service.TblMenuService;
@Service
public class TblMenuServiceImpl implements TblMenuService {

	@Override
	public List<MenuInfo> queryMenuAll() {
		return null;
	}
	
	
	
	public List<MenuInfo>  getMenuList(List<TblSysMenu> list){
		List<MenuInfo>  menuInfos=new ArrayList<MenuInfo>();
		if(CollectionUtils.isEmpty(list)){
			return menuInfos;
		}
		for (TblSysMenu tblSysMenu : list) {
			MenuInfo  menu=new MenuInfo();
			menu.setId(tblSysMenu.getId());
			menu.setText(tblSysMenu.getName());
			if(!tblSysMenu.getPid().equals("0")){
//				recursive(list);
			}
		}
		return null;
	}
	
	private  void  recursive(List<TblSysMenu> list,String id){
		for (TblSysMenu tblSysMenu : list) {
			if(!tblSysMenu.equals(0)&&tblSysMenu.getId().equals(id)){
				List<MenuInfo>  menuInfos=new ArrayList<MenuInfo>();
//				menuInfos.add
			}
		}
	}
}
