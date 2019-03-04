package com.boot.security.server.model;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public class ZtReport extends BaseEntity<Long> {

	private String author;
	private String catalog;
	private String descript;
	private Integer fId;
	private String image;
	private String name;
	private Integer pageView;
	private String pdfFile;
	private Integer sId;
	private String fIdName;
	private String sIdName;

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPageView() {
		return pageView;
	}
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	public String getPdfFile() {
		return pdfFile;
	}
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
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
}
