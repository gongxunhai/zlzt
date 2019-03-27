package com.boot.security.server.model;

import java.util.Date;


public class ZlztSubscribe extends BaseEntity<Long> {

	private Integer userId;
	private Integer agreeId;
	private Integer type;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAgreeId() {
		return agreeId;
	}
	public void setAgreeId(Integer agreeId) {
		this.agreeId = agreeId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
