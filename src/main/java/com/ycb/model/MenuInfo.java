package com.ycb.model;

import java.util.List;
/**
 * Tree实体，后期可能优化掉
 * @author chenghui
 *
 */
public class MenuInfo {
	private int id;
	private String text;
	private List<MenuInfo> list;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String iconCls;
	private String pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<MenuInfo> getList() {
		return list;
	}
	public void setList(List<MenuInfo> list) {
		this.list = list;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	
}
