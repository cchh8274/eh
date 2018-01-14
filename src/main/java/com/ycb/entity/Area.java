package com.ycb.entity;

import java.util.List;

public class Area {
    private Integer id;

    private String name;
    
    private List<Unit> list;
    
    public List<Unit> getList() {
		return list;
	}

	public void setList(List<Unit> list) {
		this.list = list;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}