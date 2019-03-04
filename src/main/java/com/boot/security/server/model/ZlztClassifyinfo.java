package com.boot.security.server.model;


import java.util.List;

public class ZlztClassifyinfo extends BaseEntity<Long> {

	private Integer parentId;
	private Integer type;
	private String name;
	private String translate;
	private String image;
	private List<ZlztClassifyinfo> children;

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

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ZlztClassifyinfo> getChildren() {
		return children;
	}

	public void setChildren(List<ZlztClassifyinfo> children) {
		this.children = children;
	}
}
