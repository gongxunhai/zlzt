package com.boot.security.server.model;

import java.util.Date;
import java.util.List;

public class KjZlSuperClassifyInfo extends BaseEntity<Long> {

	private int parentId;
	private int type;
	private String name;
	private List<KjZlSuperClassifyInfo> children;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<KjZlSuperClassifyInfo> getChildren() {
		return children;
	}

	public void setChildren(List<KjZlSuperClassifyInfo> children) {
		this.children = children;
	}
}
