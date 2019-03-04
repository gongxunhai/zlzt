package com.boot.security.server.model;

import java.util.Date;

public class ZlztCareinfo extends BaseEntity<Long> {

	private Integer userid;
	private Integer careId;
	private Integer type;

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCareId() {
		return careId;
	}
	public void setCareId(Integer careId) {
		this.careId = careId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}
