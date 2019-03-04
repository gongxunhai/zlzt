package com.boot.security.server.model;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class KjServiceNavClassifyinfo extends BaseEntity<Long> {

	private String name;
	private Integer type;
	private Integer parentId;
	private List<KjServiceNavClassifyinfo> children;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<KjServiceNavClassifyinfo> getChildren() {
		return children;
	}
	public void setChildren(List<KjServiceNavClassifyinfo> children) {
		this.children = children;
	}
}
