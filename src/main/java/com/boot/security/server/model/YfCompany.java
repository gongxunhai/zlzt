package com.boot.security.server.model;

import java.util.Date;

public class YfCompany extends BaseEntity<Long> {

	private Integer dataId;
	private Integer fId;
	private Integer sId;
	private Integer tId;
	private Integer cId;

	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public Integer getTId() {
		return tId;
	}
	public void setTId(Integer tId) {
		this.tId = tId;
	}
	public Integer getCId() {
		return cId;
	}
	public void setCId(Integer cId) {
		this.cId = cId;
	}

}
