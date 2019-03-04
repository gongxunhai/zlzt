package com.boot.security.server.model;

import java.util.Date;
import java.util.List;

public class KjProductClassifyInfo extends BaseEntity<Long> {

	private String name;
	private Integer parentId;
	private Integer type;
	private List<KjProductClassifyInfo> children;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

	public List<KjProductClassifyInfo> getChildren() {
		return children;
	}

	public void setChildren(List<KjProductClassifyInfo> children) {
		this.children = children;
	}
}
