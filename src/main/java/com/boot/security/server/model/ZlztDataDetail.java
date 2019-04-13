package com.boot.security.server.model;

import java.util.Date;

public class ZlztDataDetail extends BaseEntity<Long> {

	private Integer dataId;
	private Integer fId;
	private Integer sId;
	private Integer tId;
	private Integer cId;
	private String fIdName;
	private String sIdName;
	private String tIdName;
	private String cIdName;
	private Integer pointNum;
	private Integer careNum;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDataId() {
		return dataId;
	}

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
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
    public Integer getPointNum() {
        return pointNum;
    }
    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }
    public Integer getCareNum() {
        return careNum;
    }
    public void setCareNum(Integer careNum) {
        this.careNum = careNum;
    }

	public String getfIdName() {
		return fIdName;
	}

	public void setfIdName(String fIdName) {
		this.fIdName = fIdName;
	}

	public String getsIdName() {
		return sIdName;
	}

	public void setsIdName(String sIdName) {
		this.sIdName = sIdName;
	}

	public String gettIdName() {
		return tIdName;
	}

	public void settIdName(String tIdName) {
		this.tIdName = tIdName;
	}

	public String getcIdName() {
		return cIdName;
	}

	public void setcIdName(String cIdName) {
		this.cIdName = cIdName;
	}
}
