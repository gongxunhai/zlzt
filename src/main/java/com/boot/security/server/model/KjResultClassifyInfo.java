package com.boot.security.server.model;

import java.util.Date;
import java.util.List;

public class KjResultClassifyInfo extends BaseEntity<Long> {

	private int parentId;
	private int type;
	private String name;
	private List<KjResultClassifyInfo> children;

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

	public List<KjResultClassifyInfo> getChildren() {
		return children;
	}

	public void setChildren(List<KjResultClassifyInfo> children) {
		this.children = children;
	}
}
