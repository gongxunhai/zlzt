package com.boot.security.server.model;

import java.util.Date;

public class TblArea extends BaseEntity<Long> {

	private String code;
	private Date createDate;
	private String customaryId;
	private Integer level;
	private String name;
	private String parentId;
	private Date updateDate;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCustomaryId() {
		return customaryId;
	}
	public void setCustomaryId(String customaryId) {
		this.customaryId = customaryId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
