package com.ycb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ycb.dao.TblSysMenuMapper;
import com.ycb.entity.TblSysMenu;
import com.ycb.model.MenuInfo;
import com.ycb.service.TblMenuService;

@Service
public class TblMenuServiceImpl implements TblMenuService {
	@Autowired
	private TblSysMenuMapper tblSysMenuMapper;

	@Override
	public List<MenuInfo> queryMenuAll() {
		List<TblSysMenu> list=tblSysMenuMapper.selectMenuAll();
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		if(!CollectionUtils.isEmpty(list)){
			for (TblSysMenu menu : list) {
				MenuInfo  menuInfo=new MenuInfo();
				menuInfo.setId(menu.getId());
				menuInfo.setText(menu.getName());
				menuInfo.setIconCls(menu.getIconcls());
				if(menu.getPid().equals("0")){
					recursive(list, menuInfo);
					menuInfos.add(menuInfo);
				}
			}
		}
		return menuInfos;
	}


	private void recursive(List<TblSysMenu> list, MenuInfo info) {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		for (TblSysMenu tblSysMenu : list) {
			MenuInfo meun = new MenuInfo();
			meun.setId(tblSysMenu.getId());
			meun.setText(tblSysMenu.getName());
			meun.setUrl(tblSysMenu.getUrl());
			if (!tblSysMenu.equals("0") && tblSysMenu.getPid().equals(info.getId()+"".trim())) {
				menuInfos.add(meun);
				recursive(list,meun);
			}
		}
		info.setList(menuInfos);
	}
}
