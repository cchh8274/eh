package com.ycb.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;



public class SysResource implements java.io.Serializable {

	@Override
	public String toString() {
		return "SysResource [id=" + id + ", name=" + name + ", description=" + description + ", resourcetypeId="
				+ resourcetypeId + ", pid=" + pid + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8783421438612966130L;

	

	private String id;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdatetime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedatetime;
	private String name;
	private String url;
	private String description;
	private String iconCls;
	private Integer seq;
	private String resourcetypeId;
	private String pid;// 虚拟属性，用于获得当前资源的父资源ID



	public String getResourcetypeId() {
		return resourcetypeId;
	}

	public void setResourcetypeId(String resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		if (this.id != null) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}



	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
