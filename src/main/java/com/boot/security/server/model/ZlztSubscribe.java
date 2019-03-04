package com.boot.security.server.model;

import java.util.Date;

public class ZlztSubscribe extends BaseEntity<Long> {

	private Integer userid;
	private Integer classifyId;

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

}
