package com.boot.security.server.model;

import java.util.Date;

public class ZlztPointAgree extends BaseEntity<Long> {

	private Integer userid;
	private Integer agreeId;
	private Integer type;

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
