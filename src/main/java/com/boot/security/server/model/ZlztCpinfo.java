package com.boot.security.server.model;



public class ZlztCpinfo extends BaseEntity<Long> {

	private String cpId;
	private String name;

	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
