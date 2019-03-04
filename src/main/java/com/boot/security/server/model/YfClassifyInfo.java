package com.boot.security.server.model;


import java.util.List;

public class YfClassifyInfo extends BaseEntity<Long> {

	private Integer parentId;
	private Integer type;
	private String name;
	private List<YfClassifyInfo> children;

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<YfClassifyInfo> getChildren() {
		return children;
	}

	public void setChildren(List<YfClassifyInfo> children) {
		this.children = children;
	}
}
