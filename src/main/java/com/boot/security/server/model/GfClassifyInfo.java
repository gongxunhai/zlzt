package com.boot.security.server.model;


import java.util.List;

public class GfClassifyInfo extends BaseEntity<Long> {

	private Integer parentId;
	private Integer type;
	private String name;
	private String image;
	private List<GfClassifyInfo> children;

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

	public List<GfClassifyInfo> getChildren() {
		return children;
	}

	public void setChildren(List<GfClassifyInfo> children) {
		this.children = children;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
