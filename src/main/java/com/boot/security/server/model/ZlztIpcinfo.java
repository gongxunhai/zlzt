package com.boot.security.server.model;



public class ZlztIpcinfo extends BaseEntity<Long> {

	private String ipcId;
	private String name;

	public String getIpcId() {
		return ipcId;
	}
	public void setIpcId(String ipcId) {
		this.ipcId = ipcId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
