package com.boot.security.server.model;

import java.util.Date;

public class KjResult extends BaseEntity<Long> {

	private String area;
	private String cMan;
	private String cPhone;
	private String cWays;
	private Integer fId;
	private String knowledge;
	private String Mneed;
	private String name;
	private String predict;
	private Date pubTime;
	private Integer sId;
	private String source;
	private String tdetails;
	private String teMaturity;
	private String tIndex;
	private String tlevel;
	private String useRange;
	private String xfArea;
	private String image;
	private String fIdName;
	private String sIdName;

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

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCMan() {
		return cMan;
	}
	public void setCMan(String cMan) {
		this.cMan = cMan;
	}
	public String getCPhone() {
		return cPhone;
	}
	public void setCPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public String getCWays() {
		return cWays;
	}
	public void setCWays(String cWays) {
		this.cWays = cWays;
	}
	public Integer getFId() {
		return fId;
	}
	public void setFId(Integer fId) {
		this.fId = fId;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public String getMneed() {
		return Mneed;
	}
	public void setMneed(String Mneed) {
		this.Mneed = Mneed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPredict() {
		return predict;
	}
	public void setPredict(String predict) {
		this.predict = predict;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public Integer getSId() {
		return sId;
	}
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTdetails() {
		return tdetails;
	}
	public void setTdetails(String tdetails) {
		this.tdetails = tdetails;
	}
	public String getTeMaturity() {
		return teMaturity;
	}
	public void setTeMaturity(String teMaturity) {
		this.teMaturity = teMaturity;
	}
	public String getTIndex() {
		return tIndex;
	}
	public void setTIndex(String tIndex) {
		this.tIndex = tIndex;
	}
	public String getTlevel() {
		return tlevel;
	}
	public void setTlevel(String tlevel) {
		this.tlevel = tlevel;
	}
	public String getUseRange() {
		return useRange;
	}
	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	public String getXfArea() {
		return xfArea;
	}
	public void setXfArea(String xfArea) {
		this.xfArea = xfArea;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
